package com.shopping.store.service;

import com.shopping.store.dto.*;
import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.entity.nested.AccessoryInfo;
import com.shopping.store.entity.nested.AccessoryPrice;
import com.shopping.store.enums.AccessoryType;
import com.shopping.store.exception.DeleteNotExistedAccessoryException;
import com.shopping.store.exception.NothingToShowAccessoryException;
import com.shopping.store.exception.UnableToFindAccessoryException;
import com.shopping.store.exception.UpdateNotExistedAccessoryException;
import com.shopping.store.mapper.AccessoryMapper;
import com.shopping.store.repository.AccessoryRepository;
import com.shopping.store.service.cache.EvictCacheService;
import com.shopping.store.service.helper.AccessoryServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;

    private final AccessoryServiceHelper accessoryServiceHelper;

    private final AccessoryMapper accessoryMapper;

    private final EvictCacheService evictCacheService;

    @Override
    @Transactional
    public ViewCreatedAccessoryDto createAccessory(CreateAccessoryDto accessoryDto) {

        AccessoryGeneral accessoryGeneral = accessoryMapper.toEntity(accessoryDto);
        AccessoryGeneral complete = accessoryRepository.save(accessoryGeneral);

        log.info("CREATE -> Accessory [ID: {}, NAME: {}, TYPE: {}]", complete.getAccessoryId(),
                complete.getAccessoryInfo().getAccessoryName(),
                complete.getAccessoryInfo().getAccessoryType());

        return accessoryMapper.toViewCreatedDto(complete);
    }

    @Override
    @Transactional
    public ViewAccessoryDto viewAccessoryByArticle(UUID article) {

        Optional<AccessoryGeneral> optionalAccessoryGeneral = accessoryRepository.findByAccessoryId(article);

        if (optionalAccessoryGeneral.isEmpty()) {
            log.error("ERROR -> Reason: viewAccessoryByArticle(), Caused: {}", UnableToFindAccessoryException.class);
            throw new UnableToFindAccessoryException(String.format("Unable to find [Accessory] by ID: %s", article));
        }

        AccessoryGeneral existedAccessory = optionalAccessoryGeneral.get();

        log.info("VIEW -> Accessory by ID: {}", existedAccessory.getAccessoryId());
        return accessoryMapper.toViewDto(existedAccessory);

    }

    @Override
    @Transactional
    public List<ViewAccessoryDto> viewAllAccessories() {

        String sortingEntityField = "accessoryDate.creationDate";

        List<AccessoryGeneral> viewAccessories =
                accessoryRepository.findAll(Sort.by(DESC, sortingEntityField));

        if (viewAccessories.size() == 0) {
            log.error("ERROR -> Reason: viewAllAccessories(), Cause: {}", NothingToShowAccessoryException.class);
            throw new NothingToShowAccessoryException("Not any [Accessory] has presented.");
        }

        return viewAccessories.stream()
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ViewAccessoryDto> viewAllAccessoriesFilteredBy(AccessoryType type,
                                                               BigDecimal costFrom,
                                                               BigDecimal costTo) {
        if (costFrom != null && costTo != null) {
            return accessoryServiceHelper.findAllAccessoriesByTypeAndCostFromAndCostTo(type, costFrom, costTo);
        } else if (costFrom != null) {
            return accessoryServiceHelper.findAllAccessoriesByTypeAndCostFrom(type, costFrom);
        } else if (costTo != null) {
            return accessoryServiceHelper.findAllAccessoriesByTypeAndCostTo(type, costTo);
        } else {
            return accessoryServiceHelper.findAllAccessoriesByType(type);
        }
    }

    @Override
    @Transactional
    public ViewUpdatedAccessoryDto updateAccessoryByArticle(UUID article, UpdateAccessoryDto accessoryDto) {

        Optional<AccessoryGeneral> optionalAccessoryGeneral =
                accessoryRepository.findAccessoryBeforeManipulationByArticle(article);

        if (optionalAccessoryGeneral.isEmpty()) {
            log.error("ERROR -> Reason: updateAccessoryByArticle(), Update [Accessory] by ID: {}, Cause: {}",
                    article,
                    UpdateNotExistedAccessoryException.class);

            throw new UpdateNotExistedAccessoryException("Unfortunately, you can't update non-existed accessory!");
        }

        AccessoryGeneral accessoryGeneral = optionalAccessoryGeneral.get();

        evictCacheService.evictAccessoryByOld(
                accessoryGeneral.getAccessoryInfo().getAccessoryType(),
                accessoryGeneral.getAccessoryPrice().getAccessoryPrice()
        );

        evictCacheService.evictAccessoryByNew(
                accessoryDto.getAccessoryType(),
                accessoryDto.getAccessoryPrice()
        );

        AccessoryInfo newAccessoryInfo = new AccessoryInfo(
                accessoryDto.getAccessoryName(),
                accessoryDto.getAccessoryDescription(),
                accessoryDto.getAccessoryType()
        );

        AccessoryPrice newAccessoryPrice =
                new AccessoryPrice(accessoryDto.getAccessoryPrice(), accessoryDto.getAccessoryCurrency());

        accessoryGeneral.setAccessoryInfo(newAccessoryInfo);
        accessoryGeneral.setAccessoryPrice(newAccessoryPrice);

        log.info("UPDATE -> [Accessory] by ID: {}", article);
        return accessoryMapper.toViewUpdatedDto(accessoryGeneral);
    }

    @Override
    @Transactional
    public ViewDeletedAccessoryDto deleteAccessoryByArticle(DeleteAccessoryDto accessoryDto) {

        Optional<AccessoryGeneral> optionalAccessoryGeneral =
                accessoryRepository.findAccessoryBeforeManipulationByArticle(accessoryDto.getAccessoryId());

        if (optionalAccessoryGeneral.isEmpty()) {
            log.error("ERROR -> Reason: deleteAccessoryByArticle(), Delete [Accessory] by ID: {}, Cause: {}",
                    accessoryDto.getAccessoryId(),
                    DeleteNotExistedAccessoryException.class);

            throw new DeleteNotExistedAccessoryException("Unfortunately, you can't delete non-existed accessory!");
        }

        AccessoryGeneral deletableAccessory = optionalAccessoryGeneral.get();

        Integer deletionStatus = 1;
        accessoryRepository.delete(deletableAccessory);

        evictCacheService.evictAccessoryByOld(
                deletableAccessory.getAccessoryInfo().getAccessoryType(),
                deletableAccessory.getAccessoryPrice().getAccessoryPrice()
        );

        ViewDeletedAccessoryDto viewDeletedAccessoryDto = new ViewDeletedAccessoryDto(
                deletableAccessory.getAccessoryId(),
                deletionStatus
        );

        log.info("DELETE -> Accessory by ID: {}", accessoryDto.getAccessoryId());
        return viewDeletedAccessoryDto;
    }

}
