package com.shopping.store.controller;

import com.shopping.store.dto.*;
import com.shopping.store.enums.AccessoryType;
import com.shopping.store.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "accessory-list", allEntries = true)
    public ViewCreatedAccessoryDto createAccessory(@RequestBody @Valid CreateAccessoryDto createDto) {
        return accessoryService.createAccessory(createDto);
    }

    @GetMapping("/accessory/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(cacheNames = "accessory", key = "#article", sync = true)
    public ViewAccessoryDto viewAccessoryByArticle(@PathVariable("id") UUID article) {
        return accessoryService.viewAccessoryByArticle(article);
    }

    @GetMapping("/accessories")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(cacheNames = "accessory-list", sync = true)
    public List<ViewAccessoryDto> viewAllAccessories(
            @RequestParam(value = "filter", required = false) AccessoryType type) {
        return accessoryService.viewAllAccessories(Optional.ofNullable(type));
    }

    @PutMapping("/accessory/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Caching(evict = {
            @CacheEvict(cacheNames = "accessory", key = "#article"),
            @CacheEvict(cacheNames = "accessory-list", allEntries = true)
    })
    public ViewUpdatedAccessoryDto updateAccessoryByArticle(@PathVariable("id") UUID article,
                                                            @RequestBody @Valid UpdateAccessoryDto accessoryDto) {
        return accessoryService.updateAccessoryByArticle(article, accessoryDto);
    }

    @DeleteMapping("/accessory")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Caching(evict = {
            @CacheEvict(cacheNames = "accessory", key = "#dto.accessoryId"),
            @CacheEvict(cacheNames = "accessory-list", allEntries = true)
    })
    public ViewDeletedAccessoryDto deleteAccessoryByArticle(@RequestBody @Valid DeleteAccessoryDto dto) {
        return accessoryService.deleteAccessoryByArticle(dto);
    }

}
