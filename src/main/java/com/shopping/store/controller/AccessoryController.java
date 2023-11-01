package com.shopping.store.controller;

import com.shopping.store.dto.CreateAccessoryDto;
import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.dto.ViewCreatedAccessoryDto;
import com.shopping.store.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ViewCreatedAccessoryDto createAccessory(@RequestBody CreateAccessoryDto createDto) {
        return accessoryService.createAccessory(createDto);
    }

    @GetMapping("/accessory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ViewAccessoryDto viewAccessoryByArticle(@PathVariable("id") UUID article) {
        return accessoryService.viewAccessoryByArticle(article);
    }

}
