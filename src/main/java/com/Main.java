package com;

import com.config.FlyWayConfig;
import com.config.HibernateFactoryUtill;
import com.model.invoice.Invoice;
import com.model.phone.Phone;
import com.model.product.Manufacturer;
import com.model.product.Product;
import com.model.tablet.Tablet;
import com.repository.hibernateRepository.InvoiceRepositoryHibernate;
import com.repository.hibernateRepository.PhoneRepositoryHibernate;
import com.repository.hibernateRepository.TabletRepositoryHibernate;
import com.repository.mongoRepository.InvoiceMongoRepository;
import com.repository.mongoRepository.PhoneMongoRepository;

import javax.persistence.EntityManager;
import java.util.*;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManager entityManager = HibernateFactoryUtill.getEntityManager();
        entityManager.isOpen();
        FlyWayConfig.getInstance().migrate();
        PhoneRepositoryHibernate phoneRepositoryHibernate = new PhoneRepositoryHibernate();
        phoneRepositoryHibernate.save(new Phone("asd",22,22,"asd",Manufacturer.APPLE));

//        final Commands[] values = Commands.values();
//        boolean exit;
//
//        do {
//            exit = UserInputUtil.userAction(values);
//        } while (!exit);
//
    }
}