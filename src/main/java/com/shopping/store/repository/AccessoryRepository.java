package com.shopping.store.repository;

import com.shopping.store.entity.AccessoryGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessoryRepository extends JpaRepository<AccessoryGeneral, UUID> {

    Optional<AccessoryGeneral> findByAccessoryId(UUID id);

}
