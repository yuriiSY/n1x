package com.robots;

import com.factory.Factory;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RobotFive extends Thread {
        private static final Random RANDOM = new Random();

        @Override
        public void run() {
            while (true) {
                Factory factory = Factory.getInstance();

                AtomicInteger finalDetail = factory.getDetailFinal();
                AtomicInteger schema = factory.getSchema();
                AtomicInteger fuel = factory.getFuel();

                while (schema.get() >= 100 && finalDetail.get() < 100) {

                    int fuelNeeded = RANDOM.nextInt(350, 701);

                    System.out.println("Robot5 needs fuel in amount of: " + fuelNeeded);
                    System.out.println("Fuel present:" + fuel.get());

                    while (fuelNeeded > fuel.get());

                    int leftFuel = fuel.get() - fuelNeeded;
                    int totalOfFinalDetail = finalDetail.get() + 10;
                    System.out.println("Robot5 made " + totalOfFinalDetail +"% of detail");
                    try {
                        Thread.sleep(1000);
                        Factory.getInstance().setFuel(leftFuel);
                        Factory.getInstance().setDetailFinal(totalOfFinalDetail);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (totalOfFinalDetail == 100) {
                        Factory.getInstance().setSchema(0);
                        Factory.getInstance().setDetail(0);
                    }
                }
            }
        }
}
