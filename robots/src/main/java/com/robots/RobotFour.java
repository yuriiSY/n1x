package com.robots;


import com.factory.Factory;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RobotFour extends Thread {
    private static final Random RANDOM = new Random();

    @Override
    public void run() {
        Factory factory = Factory.getInstance();
        while (true) {
            AtomicInteger schema = factory.getSchema();
            AtomicInteger detail = factory.getDetail();

            while (schema.get() < 100 && detail.get() >= 100) {
                int total,schemaInProcess;
                schemaInProcess = RANDOM.nextInt(25, 36);
                System.out.println("Robot4 made " + schemaInProcess + "% of schema");

                if (Math.random() > 0.3) {
                    try {
                        Thread.sleep(1000);
                        total = schemaInProcess + schema.get();
                        factory.setSchema(total);
                        System.out.println("Robot4 made " + schemaInProcess + "%  of schema and total of schema is: " + total+"%");
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        Thread.sleep(1000);
                        factory.setSchema(0);
                        System.out.println("Schema crashed");
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

