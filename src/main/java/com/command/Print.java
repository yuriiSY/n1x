package com.command;


import com.model.Phone;
import com.model.ProductType;
import com.service.PhoneService;
import com.service.ProductFactory;
import com.service.ProductService;

import java.util.ArrayList;
import java.util.List;


public class Print implements Command {

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();

    @Override
    public void execute() {
        System.out.println("What do you want to print:");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        ProductFactory.print(values[userInput]);
    }
    private List<String> getNamesOfType(final ProductType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (ProductType type : values) {
            names.add(type.name());
        }
        return names;
    }
}
