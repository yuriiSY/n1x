package com.factory;

import java.util.concurrent.atomic.AtomicInteger;

public class Factory {
    private static Factory instance;
    private final AtomicInteger fuel;
    private final AtomicInteger detail;
    private final AtomicInteger schema;
    private final AtomicInteger detailFinal;

    private Factory() {
        fuel = new AtomicInteger(0);
        detail = new AtomicInteger(0);
        schema = new AtomicInteger(0);
        detailFinal = new AtomicInteger(0);
    }

    public static Factory getInstance() {
        if (instance == null) {
            synchronized (Factory.class) {
                if (instance == null) {
                    instance = new Factory();
                }
            }
        }

        return instance;
    }

    public synchronized AtomicInteger getFuel() {
        return fuel;
    }

    public synchronized AtomicInteger getDetail() {return detail;}

    public synchronized AtomicInteger getSchema() {
        return schema;
    }
    public synchronized AtomicInteger getDetailFinal() {
        return detailFinal;
    }

    public synchronized void setFuel(int count) {
        fuel.set(count);
    }

    public synchronized void setDetail(int detail) {
        this.detail.set(detail);
    }

    public synchronized void setSchema(int schema) {
        this.schema.set(schema);
    }

    public synchronized void setDetailFinal(int finalDetail) {
        detailFinal.set(finalDetail);
    }
}
