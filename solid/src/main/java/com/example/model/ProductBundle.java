package com.example.model;

import lombok.Setter;

@Setter
public class ProductBundle extends NotifiableProduct {
    protected int amount;

    @Override
    public String getBasicInfo() {
        return "ProductBundle{" +
                "channel='" + channel + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", amountInBundle=" + amount +
                '}';
    }
}