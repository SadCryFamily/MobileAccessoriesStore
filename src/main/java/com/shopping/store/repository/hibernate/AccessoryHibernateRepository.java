package com.shopping.store.repository.hibernate;

import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.enums.AccessoryType;

import java.math.BigDecimal;
import java.util.List;

public interface AccessoryHibernateRepository {

    List<AccessoryGeneral> findAllAccessoriesByType(AccessoryType type);

    List<AccessoryGeneral> findAllAccessoriesByTypeAndCostFrom(AccessoryType type, BigDecimal costFrom);

    List<AccessoryGeneral> findAllAccessoriesByTypeAndCostTo(AccessoryType type, BigDecimal costTo);

    List<AccessoryGeneral> findAllAccessoriesByTypeAndCostFromAndCostTo(AccessoryType type,
                                                                        BigDecimal costFrom,
                                                                        BigDecimal costTo);

}
