package com.shopping.store.repository.hibernate;

import com.shopping.store.entity.AccessoryGeneral;
import com.shopping.store.enums.AccessoryType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccessoryHibernateRepositoryImpl implements AccessoryHibernateRepository {

    @PersistenceContext
    private Session session;

    @Override
    @Transactional
    public List<AccessoryGeneral> findAllAccessoriesByType(AccessoryType accessoryType) {

        session.enableFilter("accessory_filter_type")
                .setParameter("type", accessoryType.name());

        return session.createQuery("FROM AccessoryGeneral").getResultList();
    }

    @Override
    @Transactional
    public List<AccessoryGeneral> findAllAccessoriesByTypeAndCostFrom(AccessoryType type, BigDecimal costFrom) {

        session.enableFilter("accessory_filter_type_and_cost_from")
                .setParameter("type", type.name())
                .setParameter("cost_from", costFrom);

        return session.createQuery("FROM AccessoryGeneral").getResultList();
    }

    @Override
    @Transactional
    public List<AccessoryGeneral> findAllAccessoriesByTypeAndCostTo(AccessoryType type, BigDecimal costTo) {

        session.enableFilter("accessory_filter_type_and_cost_to")
                .setParameter("type", type.name())
                .setParameter("cost_to", costTo);

        return session.createQuery("FROM AccessoryGeneral").getResultList();
    }

    @Override
    @Transactional
    public List<AccessoryGeneral> findAllAccessoriesByTypeAndCostFromAndCostTo(AccessoryType type, BigDecimal costFrom, BigDecimal costTo) {

        session.enableFilter("accessory_filter_type_and_cost_from_and_cost_to")
                .setParameter("type", type.name())
                .setParameter("cost_from", costFrom)
                .setParameter("cost_to", costTo);

        return session.createQuery("FROM AccessoryGeneral").getResultList();
    }
}
