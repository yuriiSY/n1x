package com.model.comparator;

import com.model.Product;

import java.util.Comparator;

public class ProductComporator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if(o2.getPrice() == o1.getPrice()){
            if (o1.getTitle().equals(o2.getTitle())){
                return Integer.compare(o1.getCount(), o2.getCount());
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
        return Double.compare(o2.getPrice(), o1.getPrice());
    }
}
