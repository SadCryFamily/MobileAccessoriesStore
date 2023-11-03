package com.shopping.store.repository;

import com.shopping.store.entity.AccessoryGeneral;

import java.util.Optional;
import java.util.UUID;

public interface AccessoryRepositoryCriteria {

    Optional<AccessoryGeneral> findAccessoryBeforeDeleteByArticle(UUID article);

    Integer deleteAccessoryByArticle(UUID article);

}
