package com.repository.hibernateRepository;

import com.config.HibernateFactoryUtill;
import com.model.Invoice;
import com.repository.crudRepository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class InvoiceRepositoryHibernate implements CrudRepository<Invoice> {
    private final EntityManager entityManager = HibernateFactoryUtill.getEntityManager();

    private static InvoiceRepositoryHibernate instance;

    public static InvoiceRepositoryHibernate getInstance() {
        if (instance == null) {
            instance = new InvoiceRepositoryHibernate();
        }
        return instance;
    }
    @Override
    public void save(Invoice product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(List<Invoice> products) {
        entityManager.getTransaction().begin();
        for (Invoice invoice : products) {
            entityManager.persist(invoice);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean update(Invoice product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Invoice.class,id));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public List<Invoice> getAll() {
        return entityManager.createQuery("SELECT i FROM Invoice i",Invoice.class).getResultList();
    }

    @Override
    public Optional<Invoice> findById(String id) {
        return Optional.ofNullable(entityManager.find(Invoice.class,id));
    }

    @Override
    public Optional<Invoice> findByTitle(String title) {
        return Optional.ofNullable(entityManager.find(Invoice.class,title));
    }

   public List<Invoice> moreThanX(double x){
        return entityManager.createQuery("SELECT p from Invoice p where p.sum > :x",Invoice.class)
                .setParameter("x",x)
                .getResultList();
   }

    public int countInvoices() {
        return (int) entityManager.createQuery("SELECT  count(id)  from Invoice").getSingleResult();
    }

    public Map<Double, Long> groupBySum() {
        Map<Double, Long> map = new HashMap<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Invoice> stud = cq.from(Invoice.class);

        cq.multiselect(stud.get("sum"),cb.count(stud)).groupBy(stud.get("sum"));
        List<Object[]> list = entityManager.createQuery(cq).getResultList();
        for(Object[] object : list){
            map.put((Double) object[0], (Long) object[1]);
        }
        return map;
    }
}
