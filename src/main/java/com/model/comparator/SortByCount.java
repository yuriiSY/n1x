package com.model.comparator;

import com.model.product.Product;

import java.util.Comparator;

public class SortByCount implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (o1.getCount() - o2.getCount());
    }
}
