package com.service;

import com.model.Product;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Container<T extends Product> {

    private static final Random RANDOM = new Random();
    List<T> products;

    public Container() {
        this.products = new LinkedList<>();
    }

    public void printerContainer(){
        for (T product : products) {
            System.out.println(product);
        }
    }

    public void sail(int numberOfProduct) {
        products.get(numberOfProduct).setPrice(products.get(numberOfProduct).getPrice() * (1-(RANDOM.nextDouble(0.2)+0.1)));
    }

    public void save(T product) {
        if (product == null) {
            throw new IllegalArgumentException();
        } else {
            products.add(product);
        }
    }

    public<I extends Number> void countRise(I number,int numberOfProduct){
        products.get(numberOfProduct).setCount(products.get(numberOfProduct).getCount() + number.intValue());
    }


}

