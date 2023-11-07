package com.shopping.store.service.cache;

import com.shopping.store.enums.AccessoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EvictCacheServiceImpl implements EvictCacheService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void evictAccessoryByOld(AccessoryType oldType, BigDecimal oldPrice) {

        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(setupKeyPattern(oldType)).build();

        Cursor<String> scanKeys = stringRedisTemplate.scan(scanOptions);

        Collection<String> keysToEvictCollection = new HashSet<>();

        while (scanKeys.hasNext()) {

            String key = scanKeys.next();

            String keyEvictPattern = "accessory-list::[A-Z]+-(\\d+(?:\\.\\d+)?)(?:-(\\d+(?:\\.\\d+)?)?)?";
            Pattern pattern = Pattern.compile(keyEvictPattern);
            Matcher matcher = pattern.matcher(key);

            if (matcher.find()) {

                Optional<String> optionalFromPrice = Optional.ofNullable(matcher.group(1));
                Optional<String> optionalToPrice = Optional.ofNullable(matcher.group(2));

                if (optionalFromPrice.isPresent() && optionalToPrice.isPresent()) {

                    BigDecimal fromPrice = new BigDecimal(optionalFromPrice.get());
                    BigDecimal toPrice = new BigDecimal(optionalToPrice.get());

                    if (oldPrice.compareTo(fromPrice) >= 0 && oldPrice.compareTo(toPrice) <= 0) {
                        keysToEvictCollection.add(key);
                    }
                }

                if (optionalFromPrice.isPresent() && optionalToPrice.isEmpty()) {

                    BigDecimal fromPrice = new BigDecimal(optionalFromPrice.get());

                    if (oldPrice.compareTo(fromPrice) >= 0) {
                        keysToEvictCollection.add(key);
                    }
                }

            }

            stringRedisTemplate.delete(keysToEvictCollection);

        }

        scanKeys.close();
    }

    @Override
    public void evictAccessoryByNew(AccessoryType newType, BigDecimal newPrice) {

        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(setupKeyPattern(newType)).build();

        Cursor<String> scanKeys = stringRedisTemplate.scan(scanOptions);

        Collection<String> keysToEvictCollection = new HashSet<>();

        while (scanKeys.hasNext()) {

            String key = scanKeys.next();

            String keyEvictPattern = "accessory-list::[A-Z]+-(\\d+(?:\\.\\d+)?)(?:-(\\d+(?:\\.\\d+)?)?)?";
            Pattern pattern = Pattern.compile(keyEvictPattern);
            Matcher matcher = pattern.matcher(key);

            if (matcher.find()) {

                Optional<String> optionalFromPrice = Optional.ofNullable(matcher.group(1));
                Optional<String> optionalToPrice = Optional.ofNullable(matcher.group(2));

                if (optionalFromPrice.isPresent() && optionalToPrice.isPresent()) {

                    BigDecimal fromPrice = new BigDecimal(optionalFromPrice.get());
                    BigDecimal toPrice = new BigDecimal(optionalToPrice.get());

                    if (newPrice.compareTo(fromPrice) >= 0 && newPrice.compareTo(toPrice) <= 0) {
                        keysToEvictCollection.add(key);
                    }
                }

                if (optionalFromPrice.isPresent() && optionalToPrice.isEmpty()) {

                    BigDecimal fromPrice = new BigDecimal(optionalFromPrice.get());

                    if (newPrice.compareTo(fromPrice) >= 0) {
                        keysToEvictCollection.add(key);
                    }
                }

            }

            stringRedisTemplate.delete(keysToEvictCollection);

        }

        scanKeys.close();
    }

    private static String setupKeyPattern(AccessoryType type) {
        return String.format("accessory-list::%s-*", type.name());
    }
}
