package com.command;


import com.model.Phone;
import com.model.ProductType;
import com.model.Tablet;
import com.model.Television;
import com.service.mainService.PhoneService;
import com.service.mainService.ProductService;
import com.service.mainService.TabletService;
import com.service.mainService.TelevisionService;

import java.util.ArrayList;
import java.util.List;


public class Print implements Command {

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TV_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Tablet> TABLET_SERVICE = TabletService.getInstance();
    @Override
    public void execute() {
        System.out.println("What do you want to print:");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        Print.print(values[userInput]);
    }
    private List<String> getNamesOfType(final ProductType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (ProductType type : values) {
            names.add(type.name());
        }
        return names;
    }

    public static void print(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.printAll();
            case TABLET -> TABLET_SERVICE.printAll();
            case TELEVISION -> TV_SERVICE.printAll();
            default -> {
                throw new IllegalArgumentException("Unknown ProductType " + type);
            }
        };
    }
}
