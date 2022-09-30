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
    public static class Builder {
    private final Tablet tablet;
    public Builder(double price, Manufacturer manufacturer) {
        if (manufacturer == null) {
            throw new IllegalArgumentException("manufacturer not null");
        }
        tablet = new Tablet();
        tablet.setPrice(price);
        tablet.setManufacturer(manufacturer);
    }
    public Builder setTitle(String title) {
        if (title.length() > 20) {
            throw new IllegalArgumentException();
        }
        tablet.setTitle(title);
        return this;
    }

    public Builder setCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException();
        }
        tablet.setCount(count);
        return this;
    }

    public Builder setModel(String model) {
        if (model.length() > 20) {
            throw new IllegalArgumentException();
        }
        tablet.setModel(model);
        return this;
    }

    public Tablet build() {
        return tablet;
    }

    }
}
