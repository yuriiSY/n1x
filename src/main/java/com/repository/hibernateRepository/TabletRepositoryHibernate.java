package com.repository.hibernateRepository;

import com.config.HibernateFactoryUtill;
import com.model.tablet.Tablet;
import com.repository.crudRepository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TabletRepositoryHibernate implements CrudRepository<Tablet> {
    private final EntityManager entityManager = HibernateFactoryUtill.getEntityManager();

    private static TabletRepositoryHibernate instance;

    public static TabletRepositoryHibernate getInstance() {
        if (instance == null) {
            instance = new TabletRepositoryHibernate();
        }
        return instance;
    }

    @Override
    public void save(Tablet product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(List<Tablet> products) {
        entityManager.getTransaction().begin();
        for (Tablet product : products) {
            entityManager.persist(product);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean update(Tablet product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Tablet.class, id));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public List<Tablet> getAll() {
        return entityManager.createQuery("select t from Tablet t", Tablet.class).getResultList();
    }

    @Override
    public Optional<Tablet> findById(String id) {
        return Optional.ofNullable(entityManager.find(Tablet.class, id));
    }

    @Override
    public Optional<Tablet> findByTitle(String title) {
        return  Optional.ofNullable(entityManager.find(Tablet.class, title));
    }
}
