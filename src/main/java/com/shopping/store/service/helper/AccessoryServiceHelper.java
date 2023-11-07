package com.shopping.store.service.helper;

import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.enums.AccessoryType;

import java.math.BigDecimal;
import java.util.List;

public interface AccessoryServiceHelper {

    List<ViewAccessoryDto> findAllAccessoriesByType(AccessoryType type);

    List<ViewAccessoryDto> findAllAccessoriesByTypeAndCostFrom(AccessoryType type, BigDecimal costFrom);

    List<ViewAccessoryDto> findAllAccessoriesByTypeAndCostTo(AccessoryType type, BigDecimal costTo);

    List<ViewAccessoryDto> findAllAccessoriesByTypeAndCostFromAndCostTo(AccessoryType type,
                                                                        BigDecimal costFrom,
                                                                        BigDecimal costTo);
}
