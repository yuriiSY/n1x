package com.model;

public class Television extends Product{

    private final String model;
    private final Manufacturer manufacturer;


    public Television(String title, int count, double price,String model,Manufacturer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
