package com.shopping.store.service;

import com.shopping.store.dto.*;
import com.shopping.store.enums.AccessoryType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccessoryService {

    ViewCreatedAccessoryDto createAccessory(CreateAccessoryDto accessoryDto);

    ViewAccessoryDto viewAccessoryByArticle(UUID article);

    List<ViewAccessoryDto> viewAllAccessories(Optional<AccessoryType> type);

    ViewDeletedAccessoryDto deleteAccessoryByArticle(DeleteAccessoryDto accessoryDto);

}
