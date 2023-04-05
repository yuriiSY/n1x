package com.example.service;

import com.example.repository.CrudRepository;
import com.example.repository.ProductRepository;

public class ProductService extends productServiceAbstract {
    private static ProductService instance;
    private ProductService(CrudRepository repository) {
        super(repository);
    }
    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService((ProductRepository.getInstance()));
        }
        return instance;
    }

}
