package com.shopping.store.service.helper;

import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.enums.AccessoryType;
import com.shopping.store.exception.NothingToShowAccessoryException;
import com.shopping.store.mapper.AccessoryMapper;
import com.shopping.store.repository.hibernate.AccessoryHibernateRepository;
import com.shopping.store.service.helper.AccessoryServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessoryServiceHelperImpl implements AccessoryServiceHelper {

    private final AccessoryHibernateRepository accessoryHibernateRepository;

    private final AccessoryMapper accessoryMapper;

    @Override
    @Transactional
    @Cacheable(cacheNames = "accessory-list", key = "#type", sync = true)
    public List<ViewAccessoryDto> findAllAccessoriesByType(AccessoryType type) {

        List<AccessoryGeneral> accessoryGenerals =
                accessoryHibernateRepository.findAllAccessoriesByType(type);

        if (accessoryGenerals.size() == 0) {
            log.error("ERROR -> Reason: viewAllAccessories(), Cause: {}", NothingToShowAccessoryException.class);
            throw new NothingToShowAccessoryException("Not any filtered [Accessory] has presented.");
        }

        log.info("GET -> List<Accessory> filtered by TYPE: {} has SIZE: {}", type, accessoryGenerals.size());
        return accessoryGenerals.stream()
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "accessory-list", key = "#type + '-' + #costFrom", sync = true)
    public List<ViewAccessoryDto> findAllAccessoriesByTypeAndCostFrom(AccessoryType type, BigDecimal costFrom) {

        List<AccessoryGeneral> accessoryGenerals =
                accessoryHibernateRepository.findAllAccessoriesByTypeAndCostFrom(type, costFrom);

        if (accessoryGenerals.size() == 0) {
            log.error("ERROR -> Reason: viewAllAccessories(), Cause: {}", NothingToShowAccessoryException.class);
            throw new NothingToShowAccessoryException("Not any filtered [Accessory] has presented.");
        }

        log.info("GET -> List<Accessory> filtered by TYPE: {} has SIZE: {}", type, accessoryGenerals.size());
        return accessoryGenerals.stream()
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "accessory-list", key = "#type + '-' + #costTo", sync = true)
    public List<ViewAccessoryDto> findAllAccessoriesByTypeAndCostTo(AccessoryType type, BigDecimal costTo) {

        List<AccessoryGeneral> accessoryGenerals =
                accessoryHibernateRepository.findAllAccessoriesByTypeAndCostTo(type, costTo);

        if (accessoryGenerals.size() == 0) {
            log.error("ERROR -> Reason: viewAllAccessories(), Cause: {}", NothingToShowAccessoryException.class);
            throw new NothingToShowAccessoryException("Not any filtered [Accessory] has presented.");
        }

        log.info("GET -> List<Accessory> filtered by TYPE: {} has SIZE: {}", type, accessoryGenerals.size());
        return accessoryGenerals.stream()
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "accessory-list", key = "#type + '-' + #costFrom + '-' + #costTo", sync = true)
    public List<ViewAccessoryDto> findAllAccessoriesByTypeAndCostFromAndCostTo(AccessoryType type, BigDecimal costFrom, BigDecimal costTo) {

        List<AccessoryGeneral> accessoryGenerals =
                accessoryHibernateRepository.findAllAccessoriesByTypeAndCostFromAndCostTo(type, costFrom, costTo);

        if (accessoryGenerals.size() == 0) {
            log.error("ERROR -> Reason: viewAllAccessories(), Cause: {}", NothingToShowAccessoryException.class);
            throw new NothingToShowAccessoryException("Not any filtered [Accessory] has presented.");
        }

        log.info("GET -> List<Accessory> filtered by TYPE: {} has SIZE: {}", type, accessoryGenerals.size());
        return accessoryGenerals.stream()
                .map(accessoryMapper::toViewDto)
                .collect(Collectors.toList());
    }
}
