package com.shopping.store.repository.criteria;

import com.shopping.store.entity.AccessoryGeneral;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccessoryRepositoryCriteriaImpl implements AccessoryRepositoryCriteria {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<AccessoryGeneral> findAccessoryBeforeManipulationByArticle(UUID article) {

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<AccessoryGeneral> criteriaQuery = criteriaBuilder.createQuery(AccessoryGeneral.class);
            Root<AccessoryGeneral> root = criteriaQuery.from(AccessoryGeneral.class);

            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("accessoryId"), article));

            Query selectAccessoryByIdQuery = entityManager.createQuery(criteriaQuery);
            selectAccessoryByIdQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

            AccessoryGeneral result = (AccessoryGeneral) selectAccessoryByIdQuery.getSingleResult();
            return Optional.ofNullable(result);

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
