package com.service.mainService;

import com.annotations.MyAutowired;
import com.model.Invoice;
import com.model.Phone;
import com.model.Product;
import com.repository.dbRepository.InvoiceRepositoryDB;
import com.repository.dbRepository.PhoneRepositoryDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoiceService {
    private final InvoiceRepositoryDB repository;
    private static InvoiceService instance;

    @MyAutowired
    private InvoiceService(final InvoiceRepositoryDB repository) {
        this.repository = repository;
    }


    public static InvoiceService getInstance() {
        if (instance == null) {
            instance = new InvoiceService(InvoiceRepositoryDB.getInstance());
        }
        return instance;
    }

    public static InvoiceService getInstance(final InvoiceRepositoryDB repository) {
        if (instance == null) {
            instance = new InvoiceService(repository);
        }
        return instance;
    }

    public void createInvoice(List<Product> products) {
        Invoice invoice = new Invoice();
        invoice.setProducts(products);
        double sum = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        invoice.setSum(sum);
        invoice.setTime(LocalDateTime.now());
        repository.save(invoice);
    }

    public List<Invoice> moreThanX(double x) {
        return repository.moreThanX(x);
    }

    public int countOfInvoice() {
        return repository.countInvoices();
    }

    public Map<Double,Integer> groupBySum() {
        return repository.groupBySum();
    }
}
