package com.service.mainService;

import com.annotations.MyAutowired;
import com.model.invoice.Invoice;
import com.model.product.Product;
import com.repository.hibernateRepository.InvoiceRepositoryHibernate;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceService {
    private final InvoiceRepositoryHibernate repository;
    private static InvoiceService instance;

    @MyAutowired
    private InvoiceService(final InvoiceRepositoryHibernate repository) {
        this.repository = repository;
    }


    public static InvoiceService getInstance() {
        if (instance == null) {
            instance = new InvoiceService(InvoiceRepositoryHibernate.getInstance());
        }
        return instance;
    }

    public static InvoiceService getInstance(final InvoiceRepositoryHibernate repository) {
        if (instance == null) {
            instance = new InvoiceService(repository);
        }
        return instance;
    }

    public void createInvoice(List<Product> products) {
        Invoice invoice = new Invoice(products);
        invoice.setProducts(products);
        double sum = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        invoice.setSum(sum);
        invoice.setTime(LocalDateTime.now());
        repository.save(invoice);
    }

//    public List<Invoice> moreThanX(double x) {
//        return repository.moreThanX(x);
//    }
//
//    public int countOfInvoice() {
//        return repository.countInvoices();
//    }
//
//    public Map<Double,Integer> groupBySum() {
//        return repository.groupBySum();
//    }
}
