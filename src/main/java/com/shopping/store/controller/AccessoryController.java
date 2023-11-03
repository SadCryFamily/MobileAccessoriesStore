package com.shopping.store.controller;

import com.shopping.store.dto.CreateAccessoryDto;
import com.shopping.store.dto.DeleteAccessoryDto;
import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.dto.ViewCreatedAccessoryDto;
import com.shopping.store.enums.AccessoryType;
import com.shopping.store.service.AccessoryService;
import com.sun.xml.bind.v2.runtime.output.DOMOutput;
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
    public Integer deleteAccessoryByArticle(@RequestBody @Valid DeleteAccessoryDto dto) {
        return accessoryService.deleteAccessoryByArticle(dto);
    }

}
