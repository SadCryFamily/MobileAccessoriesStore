package com.shopping.store.service.cache;

import com.shopping.store.enums.AccessoryType;

import java.math.BigDecimal;

public interface EvictCacheService {

    void evictAccessoryByOld(AccessoryType oldType, BigDecimal oldPrice);

    void evictAccessoryByNew(AccessoryType newType, BigDecimal newPrice);

}
