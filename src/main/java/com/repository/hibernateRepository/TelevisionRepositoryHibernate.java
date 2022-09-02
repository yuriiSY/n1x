package com.repository.hibernateRepository;

import com.config.HibernateFactoryUtill;
import com.model.Tablet;
import com.model.Television;
import com.repository.crudRepository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TelevisionRepositoryHibernate implements CrudRepository<Television> {

    private final EntityManager entityManager = HibernateFactoryUtill.getEntityManager();

    private static TelevisionRepositoryHibernate instance;

    public static TelevisionRepositoryHibernate getInstance() {
        if (instance == null) {
            instance = new TelevisionRepositoryHibernate();
        }
        return instance;
    }

    @Override
    public void save(Television product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(List<Television> products) {
        entityManager.getTransaction().begin();
        for (Television product : products) {
            entityManager.persist(product);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean update(Television product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Television.class, id));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public List<Television> getAll() {
        return entityManager.createQuery("select t from Television t", Television.class).getResultList();
    }

    @Override
    public Optional<Television> findById(String id) {
        return Optional.ofNullable(entityManager.find(Television.class, id));
    }

    @Override
    public Optional<Television> findByTitle(String title) {
        return Optional.ofNullable(entityManager.find(Television.class, title));
    }
}
