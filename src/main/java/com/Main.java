package com;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.PhoneRepository;
import com.service.OptionalService;
import com.service.PhoneService;
import com.service.TabletService;
import com.service.TelevisionService;

import java.util.Scanner;
import java.util.logging.Logger;


public class Main {

    private static final Logger LOGGER = Logger.getGlobal();
    private static final PhoneService PHONE_SERVICE = new PhoneService( new PhoneRepository());
    private static final TabletService TABLET_SERVICE = new TabletService();
    private static final TelevisionService TELEVISION_SERVICE = new TelevisionService();
    private static final OptionalService OPTIONAL_EXAMPLES = new OptionalService(new PhoneRepository());

    public static void main(String[] args) {
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

        OPTIONAL_EXAMPLES.createAndSavePhones(2);
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        System.out.println(OPTIONAL_EXAMPLES.printIfPresent(id));
        System.out.println(OPTIONAL_EXAMPLES.printIfPresent(""));
        System.out.println(OPTIONAL_EXAMPLES.printOrGetDefault(id));
        System.out.println(OPTIONAL_EXAMPLES.printOrGetDefault("asd"));
        OPTIONAL_EXAMPLES.printOrCreatDefault(id);
        OPTIONAL_EXAMPLES.printOrCreatDefault("ads");
        System.out.println(OPTIONAL_EXAMPLES.mapPhoneToString(id));
        System.out.println(OPTIONAL_EXAMPLES.mapPhoneToString("sd"));
        OPTIONAL_EXAMPLES.printOrPrintDefault(id);
        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 1000);
        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 10);
        OPTIONAL_EXAMPLES.checksPhoneLessThen("123", 1000);
        try {
            OPTIONAL_EXAMPLES.printPhoneOrElseThrowException(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        OPTIONAL_EXAMPLES.printPhone(id);

    }
}
