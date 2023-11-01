package com.shopping.store.service;

import com.shopping.store.dto.CreateAccessoryDto;
import com.shopping.store.dto.ViewAccessoryDto;
import com.shopping.store.dto.ViewCreatedAccessoryDto;

import java.util.UUID;

public interface AccessoryService {

    ViewCreatedAccessoryDto createAccessory(CreateAccessoryDto accessoryDto);

    ViewAccessoryDto viewAccessoryByArticle(UUID article);

}
