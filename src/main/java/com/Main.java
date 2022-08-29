package com;

import com.model.invoice.Invoice;
import com.model.phone.Phone;
import com.model.product.Manufacturer;
import com.model.product.Product;
import com.repository.mongoRepository.InvoiceMongoRepository;
import com.repository.mongoRepository.PhoneMongoRepository;

import java.util.*;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

    //    MongoDatabase mongoDatabase = MongoConfig.connect("n1x");
        PhoneMongoRepository phoneMongo = new PhoneMongoRepository();
        InvoiceMongoRepository invoiceMongoRepository = new InvoiceMongoRepository();
        List<Product> products = Arrays.asList(new Phone("Title1", 4, 12, "Model", Manufacturer.APPLE),new Phone("Title2", 4, 0.0, "Model", Manufacturer.APPLE));
        invoiceMongoRepository.save(new Invoice(products));
        Map<Double,Long> map = invoiceMongoRepository.groupBy();
        System.out.println(map);
//        PhoneRepositoryHibernate phoneRepositoryHibernate = new PhoneRepositoryHibernate();
//        phoneRepositoryHibernate.save(new Phone("Title1", 4, 0.0, "Model", Manufacturer.APPLE));
//        String s = SCANNER.nextLine();
//        System.out.println(phoneRepositoryHibernate.findById(s));
//        final Commands[] values = Commands.values();
//        boolean exit;
//
//        do {
//            exit = UserInputUtil.userAction(values);
//        } while (!exit);
//
//        InvoiceService invoiceService = InvoiceService.getInstance();
//        List<Product> products = new ArrayList<>();
//        ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
//        products.add(PHONE_SERVICE.getAll().get(0));
//        products.add(PHONE_SERVICE.getAll().get(1));
//        invoiceService.createInvoice(products);
//        invoiceService.createInvoice(products);
//
//        InvoiceRepositoryHibernate invoiceRepositoryHibernate = new InvoiceRepositoryHibernate();
//        double S = SCANNER.nextDouble();
//        System.out.println(invoiceRepositoryHibernate.moreThanX(S));
//        System.out.println(invoiceRepositoryHibernate.groupBySum());
////        InvoiceService invoiceService = InvoiceService.getInstance();
//        ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
//        ProductService<Television> TV_SERVICE = TelevisionService.getInstance();
//        List<Product> products = new ArrayList<>();
//        products.add(PHONE_SERVICE.getAll().get(2));
//        products.add(TV_SERVICE.getAll().get(1));
//        invoiceService.createInvoice(products);
//        System.out.println(invoiceService.moreThanX(32));
//        System.out.println(invoiceService.countOfInvoice());
//        System.out.println(invoiceService.groupBySum());

//        Handler handler = new Handler();
//        System.out.println(handler.createCache());

//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        InputStream inputStreamXml = loader.getResourceAsStream("phone.xml");
//        PhoneService PHONE_SERVICE = PhoneService.getInstance();
//
//        Phone phone = PHONE_SERVICE.xmlPhoneFromMap(inputStreamXml);
//        System.out.println(phone.toString());
//
//
//        System.out.println("===============");
//
//        InputStream inputStreamJson = loader.getResourceAsStream("phone.json");
//        Phone phone1 = PHONE_SERVICE.jsonPhoneFromMap(inputStreamJson);
//        System.out.println(phone1.toString());
///////////////

//        ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
//        TelevisionService p = TelevisionService.getInstance();
//
//        System.out.println("Map to product");
//        Map<String, Object> map = new HashMap<>();
//        map.put("title", "Phone - 222");
//        map.put("model", "Model - 88");
//        map.put("price", 123);
//        map.put("count", 48);
//        Phone po = PHONE_SERVICE.productFromMap(map);
//        System.out.println(po.toString());
//
//        PHONE_SERVICE.createAndSave(5);
//        PHONE_SERVICE.printAll();
//        System.out.println("PREDICATE");
//        System.out.println(PHONE_SERVICE.isValid.test(PHONE_SERVICE.getAll()));
//        PHONE_SERVICE.save(new Phone("Title1", 4, 0.0, "Model", Manufacturer.APPLE));
//
//        System.out.println("Products which does not have Price");
//        List<Phone> noPrice = PHONE_SERVICE.isPrice();
//        System.out.println(noPrice);
//
//        System.out.println("Summary Statistics");
//        System.out.println(PHONE_SERVICE.summaryStatistics());
//
//        System.out.print("(filter)enter price: ");
//        Double n = SCANNER.nextDouble();
//        System.out.println();
//        List<Phone> l = PHONE_SERVICE.filterMoreThan(n);
//        System.out.println("Map:" + l);
//
//        System.out.println("DETAILS");
//        String s = SCANNER.next();
//        System.out.println(p.findDetail(s));
//

//        Tree tree = new Tree(new ProductComporator());
//        tree.add(new Phone("Title2", 4, 8.0, "Model", Manufacturer.APPLE));
//        tree.add(new Phone("Title1", 4, 9.0, "Model", Manufacturer.APPLE));
//        tree.add(new Phone("Title3", 4, 6.0, "Model", Manufacturer.APPLE));
//        tree.add(new Phone("Title4", 4, 5.0, "Model", Manufacturer.APPLE));
//        tree.add(new Phone("Title5", 4, 4.0, "Model", Manufacturer.APPLE));
//        tree.add(new Phone("Title4", 4, 2.0, "Model", Manufacturer.APPLE));
//        tree.add(new Phone("Title1", 4, 10.0, "Model", Manufacturer.APPLE));
//        System.out.println(tree.sumOfPricesRightBranch());
//        System.out.println(tree.sumOfPricesLeftBranch());
//        tree.printTree();


//        ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
//        ProductService<Phone> TV_SERVICE = PhoneService.getInstance();
//        List<Product> products = new ArrayList<>();
//        products.add(PHONE_SERVICE.getAll().get(0));
//        products.add(TV_SERVICE.getAll().get(0));
//        invoiceService.createInvoice(products);
//        System.out.println("try");
//
//        System.out.println(invoiceService.moreThanX(32));
//        System.out.println(invoiceService.countOfInvoice());
//        System.out.println(invoiceService.groupBySum());
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
*/
    }
}