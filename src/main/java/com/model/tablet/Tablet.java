package com.model.tablet;

import com.model.product.Manufacturer;
import com.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Tablet extends Product {
    @Column
    private String model;
    @Column
    private Manufacturer manufacturer;

    public Tablet(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title,count,price);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Tablet() {

    }

    @Override
    public String toString() {
        return "Tablet{" +
                "model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
