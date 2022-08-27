package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Television extends Product{

    @Column
    private  String model;
    @Column
    private  Manufacturer manufacturer;

   // private List<String> details;


    public Television(String title, int count, double price,String model,Manufacturer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    //    this.details = details;
    }

    public Television() {

    }

//    public List<String> getDetails() {
//        return details;
//    }

//    public void setDetails(List<String> details) {
//        this.details = details;
//    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}' ;
    }
}
