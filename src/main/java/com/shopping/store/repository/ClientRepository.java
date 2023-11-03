package com.shopping.store.repository;

import com.shopping.store.entity.ClientGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientGeneral, UUID> {
}
