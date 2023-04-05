package com;

import com.factory.Factory;
import com.robots.RobotFive;
import com.robots.RobotFour;
import com.robots.RobotOne;
import com.robots.RobotTwoAndThree;

public class Main {

    public static void main(String[] args) {

        RobotOne robotOne = new RobotOne();
        RobotTwoAndThree robotTwo = new RobotTwoAndThree(2);
        RobotTwoAndThree robotThree = new RobotTwoAndThree(3);
        RobotFour robotFour = new RobotFour();
        RobotFive robotFive = new RobotFive();

        robotOne.setDaemon(true);
        robotTwo.setDaemon(true);
        robotThree.setDaemon(true);
        robotFour.setDaemon(true);
        robotFive.setDaemon(true);

        robotOne.start();
        robotTwo.start();
        robotThree.start();
        robotFour.start();
        robotFive.start();

        while(Factory.getInstance().getDetailFinal().get() < 100);

        System.out.println("One detail was created!");
    }
}
