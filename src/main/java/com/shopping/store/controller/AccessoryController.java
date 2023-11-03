package com.shopping.store.controller;

import com.shopping.store.dto.*;
import com.shopping.store.enums.AccessoryType;
import com.shopping.store.service.AccessoryService;
import lombok.RequiredArgsConstructor;
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
    public ViewCreatedAccessoryDto createAccessory(@RequestBody @Valid CreateAccessoryDto createDto) {
        return accessoryService.createAccessory(createDto);
    }

    @GetMapping("/accessory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ViewAccessoryDto viewAccessoryByArticle(@PathVariable("id") UUID article) {
        return accessoryService.viewAccessoryByArticle(article);
    }

    @GetMapping("/accessories")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewAccessoryDto> viewAllAccessories(
            @RequestParam(value = "filter", required = false) AccessoryType type) {
        return accessoryService.viewAllAccessories(Optional.ofNullable(type));
    }

    @DeleteMapping("/accessory")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ViewDeletedAccessoryDto deleteAccessoryByArticle(@RequestBody @Valid DeleteAccessoryDto dto) {
        return accessoryService.deleteAccessoryByArticle(dto);
    }

}
