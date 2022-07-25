package com.service;

import com.model.Phone;
import com.model.ProductType;

import java.util.Random;

public class ProductFactory {
    private static final Random RANDOM = new Random();

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();

    private ProductFactory() {
    }

    public static void createAndSave(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.createAndSave(1);
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }

    public static void deleteLastAdd(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.delete(PHONE_SERVICE.getAll().get(PHONE_SERVICE.getAll().size()-1));
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }

    public static void print(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.printAll();
            default -> {
                throw new IllegalArgumentException("Unknown ProductType " + type);
            }
        };
    }


    public static void update(ProductType type, int id) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.update(PHONE_SERVICE.getAll().get(id));
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }
}
