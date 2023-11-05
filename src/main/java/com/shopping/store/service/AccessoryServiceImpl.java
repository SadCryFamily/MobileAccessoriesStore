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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;

    private final AccessoryMapper accessoryMapper;

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
    public List<ViewAccessoryDto> viewAllAccessories(Optional<AccessoryType> type) {

        List<AccessoryGeneral> viewAccessories =
                accessoryRepository.findAll(Sort.by(Sort.Direction.DESC, "accessoryDate.creationDate"));

        if (viewAccessories.size() == 0) {
            log.error("ERROR -> Reason: viewAllAccessories(), Cause: {}", NothingToShowAccessoryException.class);
            throw new NothingToShowAccessoryException("Not any [Accessory] has presented.");
        }

        return (type.isPresent()) ?
                (this.getAllAccessoriesByFilter(viewAccessories, type)) :
                (this.getAllAccessoriesDefault(viewAccessories));
    }

    @Override
    @Transactional
    public ViewUpdatedAccessoryDto updateAccessoryByArticle(UUID article, UpdateAccessoryDto accessoryDto) {

        AccessoryGeneral accessoryGeneral =
                accessoryRepository.findAccessoryBeforeDeleteByArticle(article)
                        .orElseThrow(() -> {

                            log.error("ERROR -> Reason: updateAccessoryByArticle(), Update [Accessory] by ID: {}, Caused: {}",
                                    article,
                                    UpdateNotExistedAccessoryException.class);

                            return new UpdateNotExistedAccessoryException("Unfortunately, you can't update non-existed accessory!");
                        });

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
        return new ViewUpdatedAccessoryDto(
                accessoryGeneral.getAccessoryId(), accessoryGeneral.getAccessoryInfo(),
                accessoryGeneral.getAccessoryPrice(), accessoryGeneral.getAccessoryDate()
        );
    }

    @Override
    @Transactional
    public ViewDeletedAccessoryDto deleteAccessoryByArticle(DeleteAccessoryDto accessoryDto) {

        AccessoryGeneral deletableAccessory =
                accessoryRepository
                        .findAccessoryBeforeDeleteByArticle(accessoryDto.getAccessoryId())
                        .orElseThrow(() -> {

                            log.error("ERROR -> Reason: deleteAccessoryByArticle(), Trying delete empty [Accessory] by ID: {}, Cause: {}",
                                    accessoryDto.getAccessoryId(),
                                    DeleteNotExistedAccessoryException.class);

                            return new DeleteNotExistedAccessoryException("Unfortunately, you can't delete non-existed accessory!");
                        });

        Integer deletionStatus = 1;
        accessoryRepository.delete(deletableAccessory);

        ViewDeletedAccessoryDto viewDeletedAccessoryDto = new ViewDeletedAccessoryDto(
                deletableAccessory.getAccessoryId(),
                deletionStatus
        );

        log.info("DELETE -> Accessory by ID: {}", accessoryDto.getAccessoryId());
        return viewDeletedAccessoryDto;
    }

    private List<ViewAccessoryDto> getAllAccessoriesByFilter(List<AccessoryGeneral> viewAccessories, Optional<AccessoryType> type) {

        log.info("VIEW -> List<Accessory> using filtering of: {}. Total accessories: {}",type.get(), viewAccessories.size());
        return viewAccessories.stream()
                .filter(accessory -> accessory.getAccessoryInfo().getAccessoryType() == type.get())
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());
    }

    private List<ViewAccessoryDto> getAllAccessoriesDefault(List<AccessoryGeneral> viewAccessories) {

        log.info("VIEW -> List<Accessory>. Total accessories: {}", viewAccessories.size());
        return viewAccessories.stream()
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());
    }
}
