package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Phone extends Product {
    @Column
    private  String model;
    @Column
    private  Manufacturer manufacturer;
    //private OperationSystem operationSystem;
    @Column
    private LocalDateTime time;

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    }
    public Phone(String title, int count, double price, String model, Manufacturer manufacturer, OperationSystem operationSystem, LocalDateTime time) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
      //  this.operationSystem = operationSystem;
        this.time = time;
    }

    public Phone() {

    }

    @Override
    public String toString() {
        return "Phone{" +
                "model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
        //        ", operationSystem=" + operationSystem +
                ", time=" + time +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}' ;
    }
}
