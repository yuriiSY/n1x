package com.robots;

import com.factory.Factory;

import java.util.Random;

public class RobotOne extends Thread{
    private static final Random RANDOM = new Random();

    @Override
    public void run() {
        Factory factory = Factory.getInstance();
        while (true) {
            int fuelCount = RANDOM.nextInt(500, 1001);
            System.out.println("Robot1 got fuel: " + fuelCount );
            try {
                Thread.sleep(3000);
                int totalFuel = factory.getFuel().get() + fuelCount;
                Factory.getInstance().setFuel(totalFuel);
                System.out.println("Robot1 delivered fuel and total of fuel is: " + totalFuel);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
