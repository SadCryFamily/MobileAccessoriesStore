package com.shopping.store.repository;

import com.shopping.store.entity.AccessoryGeneral;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessoryRepository extends JpaRepository<AccessoryGeneral, UUID> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<AccessoryGeneral> findByAccessoryId(UUID id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<AccessoryGeneral> findAll(Sort sort);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Integer removeByAccessoryId(UUID id);

}
