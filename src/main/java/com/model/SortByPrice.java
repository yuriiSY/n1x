package com.model;

import java.util.Comparator;

public class SortByPrice implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
