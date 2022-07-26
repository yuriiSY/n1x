package com.service;

import com.model.*;
import com.model.comparator.ProductComporator;
import com.model.comparator.SortByCount;
import com.model.comparator.SortByName;
import com.model.comparator.SortByPrice;
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

    public void delete(T product){
       repository.delete(product.getId());
    }

    public void update(T product){
        repository.update(product);
    }


    public void sortProduct(){
        Collections.sort(repository.getAll(),new ProductComporator());
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
