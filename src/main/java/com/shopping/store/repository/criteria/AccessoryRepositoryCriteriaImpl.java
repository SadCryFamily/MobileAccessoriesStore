package com.shopping.store.repository.criteria;

import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.repository.criteria.AccessoryRepositoryCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccessoryRepositoryCriteriaImpl implements AccessoryRepositoryCriteria {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<AccessoryGeneral> findAccessoryBeforeDeleteByArticle(UUID article) {

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

    @Override
    @Transactional
    public Integer deleteAccessoryByArticle(UUID article) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<AccessoryGeneral> delete = criteriaBuilder.createCriteriaDelete(AccessoryGeneral.class);
        Root<AccessoryGeneral> root = delete.from(AccessoryGeneral.class);

        delete.where(criteriaBuilder.equal(root.get("accessoryId"), article));

        Query deleteAccessoryQuery = entityManager.createQuery(delete);

        return deleteAccessoryQuery.executeUpdate();
    }

    @Override
    @Transactional
    public Integer updateAccessoryByArticle(UUID article) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<AccessoryGeneral> update = criteriaBuilder.createCriteriaUpdate(AccessoryGeneral.class);
        Root<AccessoryGeneral> root = update.from(AccessoryGeneral.class);

        update.where(criteriaBuilder.equal(root.get("accessoryId"), article));

        Query updateAccessoryQuery = entityManager.createQuery(update);

        return updateAccessoryQuery.executeUpdate();
    }
}
