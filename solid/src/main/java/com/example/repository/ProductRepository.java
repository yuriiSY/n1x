package com.example.repository;

import com.example.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements CrudRepository<Product>{
    private static ProductRepository instance;
    private Map<Long, Product> storage;

    private ProductRepository() {
        this.storage = new HashMap<>();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public Product save(Product product) {
        return storage.put(product.getId(), product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(storage.values());
    }
}
