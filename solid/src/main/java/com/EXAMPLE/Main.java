package com.EXAMPLE;

import com.EXAMPLE.model.NotifiableProduct;
import com.EXAMPLE.model.Product;
import com.EXAMPLE.model.ProductBundle;
import com.EXAMPLE.service.NotificationService;
import com.EXAMPLE.service.ProductFactory;
import com.EXAMPLE.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductService productService = ProductService.getInstance();
        NotificationService notificationService = NotificationService.getInstance();
        ProductFactory productFactory = ProductFactory.getInstance();
        List<Product> products = new ArrayList<>();
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.forEach(productService::save);
        System.out.println(productService.getAll());
        System.out.println("notifications sent: " + notificationService.filterNotifiableProductsAndSendNotifications());
    }
}
