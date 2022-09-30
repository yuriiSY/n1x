package com.EXAMPLE.service;

import com.EXAMPLE.model.Product;
import com.EXAMPLE.repository.CrudRepository;

import java.util.List;

public abstract class productServiceAbstract<T extends Product>{
    private final CrudRepository<T> repository;

    public productServiceAbstract(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void save(T product) {
        repository.save(product);
    }

    public List<T> getAll() {
        return repository.getAll();
    }
}
