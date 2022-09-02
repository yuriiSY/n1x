package com.command;

import com.model.phone.Phone;
import com.model.product.ProductType;
import com.model.tablet.Tablet;
import com.model.television.Television;
import com.service.mainService.PhoneService;
import com.service.mainService.ProductService;
import com.service.mainService.TabletService;
import com.service.mainService.TelevisionService;

import java.util.ArrayList;
import java.util.List;

public class Update implements Command {


    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TV_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Tablet> TABLET_SERVICE = TabletService.getInstance();
    @Override
    public void execute() {
        System.out.println("What do you want to update:");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        Print.print(values[userInput]);
        final int userInput1 = UserInputUtil.getUserInput(0,names);
        Update.update(values[userInput],userInput1);
    }

    private List<String> getNamesOfType(final ProductType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (ProductType type : values) {
            names.add(type.name());
        }
        return names;
    }

    public static void update(ProductType type, int id) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.update(PHONE_SERVICE.getAll().get(id));
            case TABLET -> TABLET_SERVICE.update(TABLET_SERVICE.getAll().get(id));
            case TELEVISION -> TV_SERVICE.update(TV_SERVICE.getAll().get(id));
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }
}
