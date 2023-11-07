package com.shopping.store.generator;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class AccessoryListCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {

        String cacheName = "accessory-list";
        String currentDate = LocalDateTime.now().toString();

        return String.format("%s::%s", cacheName, currentDate);
    }
}
