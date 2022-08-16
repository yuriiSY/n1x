package com.model;

import java.util.ArrayList;
import java.util.List;

public class Television extends Product{

    private final String model;
    private final Manufacturer manufacturer;

    private List<String> details;


    public Television(String title, int count, double price,String model,Manufacturer manufacturer,List<String> details) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", details=" + details +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}' ;
    }
}
