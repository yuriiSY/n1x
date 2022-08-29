package com.repository.hibernateRepository;

import com.config.HibernateFactoryUtill;
import com.model.phone.Phone;
import com.repository.crudRepository.CrudRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PhoneRepositoryHibernate implements CrudRepository<Phone> {

    private final EntityManager entityManager = HibernateFactoryUtill.getEntityManager();

    private static PhoneRepositoryHibernate instance;

    public static PhoneRepositoryHibernate getInstance() {
        if (instance == null) {
            instance = new PhoneRepositoryHibernate();
        }
        return instance;
    }


    @Override
    public void save(Phone product) {
        entityManager.getTransaction().begin();
        product.setTime(LocalDateTime.now());
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(List<Phone> products) {
        entityManager.getTransaction().begin();
        for (Phone product : products) {
            entityManager.persist(product);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean update(Phone product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Phone.class, id));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public List<Phone> getAll() {
        return entityManager.createQuery("select p from Phone p", Phone.class).getResultList();
    }

    @Override
    public Optional<Phone> findById(String id) {
        return Optional.ofNullable(entityManager.find(Phone.class, id));
    }

    @Override
    public Optional<Phone> findByTitle(String title) {
        return  Optional.ofNullable(entityManager.find(Phone.class, title));
    }
}