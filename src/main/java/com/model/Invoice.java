package com.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Invoice {

    private String id;
    private double sum;
    private List<Product> products;
    private LocalDateTime time;

    public Invoice() {
        this.id = UUID.randomUUID().toString();
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", products=" + products +
                ", time=" + time +
                '}';

    }
}
