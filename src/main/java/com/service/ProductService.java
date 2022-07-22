package com.service;

import com.model.Product;
import com.model.SortByCount;
import com.model.SortByName;
import com.model.SortByPrice;
import com.repository.CrudRepository;


import java.util.*;


public abstract class ProductService <T extends Product>{

    protected static final Random RANDOM = new Random();
    protected final CrudRepository<T> repository;

    protected ProductService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void createAndSave(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count must been bigger then 0");
        }
        List<T> products = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final T phone = creatProduct();
            products.add(phone);
        }
        repository.saveAll(products);
    }

    protected abstract T creatProduct();

    public void save(T phone) {
        if (phone.getCount() == 0) {
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public void printAll() {
        for (T phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public void sortPrice(){
        Collections.sort(repository.getAll(),new SortByPrice());
    }

    public void sortName(){
        Collections.sort(repository.getAll(),new SortByName());
    }
    public void sortCount(){
        Collections.sort(repository.getAll(),new SortByCount());
    }
}
