package com.shopping.store.repository.criteria;

import com.shopping.store.entity.AccessoryGeneral;

import java.util.Optional;
import java.util.UUID;

public interface AccessoryRepositoryCriteria {

    Optional<AccessoryGeneral> findAccessoryBeforeManipulationByArticle(UUID article);

}
