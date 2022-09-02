package com.repository.crudRepository;

import com.model.Invoice;

import java.util.List;
import java.util.Map;

public interface CrudInvoiceRepository{
    void save(Invoice invoice);

    List<Invoice> moreThanX(double x);

    List<Invoice> getAll();
    int countInvoices();

    void updateInvoiceByID(Invoice invoice);

    Map<Double, Integer> groupBySum();
}
