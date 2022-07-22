package com.model;

import java.util.Comparator;

public class SortByName implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
