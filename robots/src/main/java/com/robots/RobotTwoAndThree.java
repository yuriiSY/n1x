package com.robots;


import com.factory.Factory;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RobotTwoAndThree extends Thread{
    private static final Random RANDOM = new Random();
    private final int robotNumber;
    public RobotTwoAndThree(int robotNumber) {
        this.robotNumber = robotNumber;
    }

    @Override
    public void run() {
        Factory factory = Factory.getInstance();
        while (true) {
            AtomicInteger detail = factory.getDetail();
            while (detail.get() < 100) {
                int detailInProcess = RANDOM.nextInt(10, 21);
                System.out.println("Robot" + robotNumber + " made " + detailInProcess + "% of detail");
                try {
                    Thread.sleep(2000);
                    int totalDetailInProcess = detail.get() + detailInProcess;
                    System.out.println("Robot" + robotNumber + " made " + totalDetailInProcess + "% of Total detail");
                    factory.setDetail(totalDetailInProcess);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
