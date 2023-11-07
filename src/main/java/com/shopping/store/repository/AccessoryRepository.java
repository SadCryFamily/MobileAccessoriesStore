package com.shopping.store.repository;

import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.repository.criteria.AccessoryRepositoryCriteria;
import com.shopping.store.repository.hibernate.AccessoryHibernateRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessoryRepository extends JpaRepository<AccessoryGeneral, UUID>,
        AccessoryRepositoryCriteria {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<AccessoryGeneral> findByAccessoryId(UUID id);


}
