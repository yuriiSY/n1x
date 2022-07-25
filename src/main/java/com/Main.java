package com;

import com.command.Command;
import com.command.Commands;
import com.model.*;
import com.model.comparator.ProductComporator;
import com.repository.PhoneRepository;
import com.repository.TabletRepository;
import com.repository.TelevisionRepository;
import com.service.*;

import java.util.*;
import java.util.logging.Logger;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Tree tree = new Tree(new ProductComporator());
        tree.add(new Phone("Title2", 4, 8.0, "Model", Manufacturer.APPLE));
        tree.add(new Phone("Title1", 4, 9.0, "Model", Manufacturer.APPLE));
        tree.add(new Phone("Title3", 4, 6.0, "Model", Manufacturer.APPLE));
        tree.add(new Phone("Title4", 4, 5.0, "Model", Manufacturer.APPLE));
        tree.add(new Phone("Title5", 4, 4.0, "Model", Manufacturer.APPLE));
        tree.add(new Phone("Title4", 4, 2.0, "Model", Manufacturer.APPLE));
        System.out.println(tree.sumOfPricesRightBranch());
        System.out.println(tree.sumOfPricesLeftBranch());
        tree.printTree();

        /*final Commands[] values = Commands.values();
        boolean exit;

        do {
            exit = userAction(values);
        } while (!exit);
*/

        /*
        Order order = new Order();
        order.add(new Phone("Title0", 6, 6.0, "Model", Manufacturer.APPLE));
        order.add(new Phone("Title1", 2, 3.0, "Model", Manufacturer.APPLE));
        order.add(new Phone("Title2", 4, 5.0, "Model", Manufacturer.APPLE));
        System.out.println(order.getDateOfVersion(0));
        System.out.println(order.getDateOfLastVersion());
        System.out.println(order.getDateOFirstVersion());
        System.out.println(order.size());
        System.out.println("---------------------");

        for (Product o : order) {
            System.out.println(o);
        }


/*
        PHONE_SERVICE.createAndSave(5);
        PHONE_SERVICE.printAll();
        PHONE_SERVICE.sortProduct();
        PHONE_SERVICE.printAll();

        TABLET_SERVICE.createAndSave(2);
        TABLET_SERVICE.printAll();
        TELEVISION_SERVICE.createAndSave(2);
        TELEVISION_SERVICE.printAll();


        System.out.println("-".repeat(10));

        /* LOGGER.info("creation of telephones");
        System.out.println("Phone models:");
        PHONE_SERVICE.createAndSavePhones(5);
        PHONE_SERVICE.printAll();
        LOGGER.info("creation of tablets");
        System.out.println("Tablet models:");
        TABLET_SERVICE.createAndSaveTablet(5);
        TABLET_SERVICE.printAll();
        LOGGER.info("creation of televisions");
        System.out.println("Television models:");
        TELEVISION_SERVICE.createAndSaveTelevision(5);
        TELEVISION_SERVICE.printAll();



        Scanner scanner = new Scanner(System.in);
        String id = PHONE_SERVICE.getAll().get(0).getId();

        System.out.println();
        OPTIONAL_EXAMPLES.printIfPresent(id);
        System.out.println();
        PHONE_SERVICE.deletePhoneById(id);
        LOGGER.info("delete phone");
        PHONE_SERVICE.printAll();

        String title = scanner.next();
        int count = scanner.nextInt();
        double price = scanner.nextDouble();
        String model = scanner.next();
        Manufacturer manufacturer = Manufacturer.APPLE;

        PHONE_SERVICE.updatePhone(new Phone(title,count,price,model,manufacturer));
        PHONE_SERVICE.printAll();*/

//        OPTIONAL_EXAMPLES.createAndSavePhones(2);
//        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
//        System.out.println(OPTIONAL_EXAMPLES.printIfPresent(id));
//        System.out.println(OPTIONAL_EXAMPLES.printIfPresent(""));
//        System.out.println(OPTIONAL_EXAMPLES.printOrGetDefault(id));
//        System.out.println(OPTIONAL_EXAMPLES.printOrGetDefault("asd"));
//        OPTIONAL_EXAMPLES.printOrCreatDefault(id);
//        OPTIONAL_EXAMPLES.printOrCreatDefault("ads");
//        System.out.println(OPTIONAL_EXAMPLES.mapPhoneToString(id));
//        System.out.println(OPTIONAL_EXAMPLES.mapPhoneToString("sd"));
//        OPTIONAL_EXAMPLES.printOrPrintDefault(id);
//        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 1000);
//        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 10);
//        OPTIONAL_EXAMPLES.checksPhoneLessThen("123", 1000);
//        try {
//            OPTIONAL_EXAMPLES.printPhoneOrElseThrowException(id);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//        OPTIONAL_EXAMPLES.printPhone(id);
//
//        OPTIONAL_EXAMPLES.createAndSavePhones(1);
//        Phone phone = OPTIONAL_EXAMPLES.getAll().get(0);
/*        Container container = new Container();
        container.save(new Phone("Title", 1, 10, "Model", Manufacturer.APPLE));
        container.save(new Phone("Title", 1, 100, "Model", Manufacturer.APPLE));
        container.printerContainer();
        container.sail(0);
        container.printerContainer();

        container.countRise(2.0, 1);
        container.printerContainer();
    }

    private static boolean userAction(final Commands[] values) {
        int userCommand = -1;
        do {
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i].getName());
            }
            int input = SCANNER.nextInt();
            if (input >= 0 && input < values.length) {
                userCommand = input;
            }
        } while (userCommand == -1);
        final Command command = values[userCommand].getCommand();
        if (command == null) {
            return true;
        } else {
            command.execute();
            return false;
        }
    }
    */

    }
}