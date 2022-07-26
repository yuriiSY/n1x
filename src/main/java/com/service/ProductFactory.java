package com.service;

import com.model.Phone;
import com.model.ProductType;
import com.model.Tablet;
import com.model.Television;

import java.util.Random;

public class ProductFactory {
    private static final Random RANDOM = new Random();

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TV_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Tablet> TABLET_SERVICE = TabletService.getInstance();

    private ProductFactory() {
    }

    public static void createAndSave(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.createAndSave(1);
            case TABLET -> TABLET_SERVICE.createAndSave(1);
            case TELEVISION -> TABLET_SERVICE.createAndSave(1);
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }
}
