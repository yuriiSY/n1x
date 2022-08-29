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

public class Delete implements Command{
    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<Television> TV_SERVICE = TelevisionService.getInstance();
    private static final ProductService<Tablet> TABLET_SERVICE = TabletService.getInstance();
   @Override
    public void execute() {
        System.out.println("What do you want to delete:");
        final ProductType[] values = ProductType.values();
        final List<String> names = getNamesOfType(values);
        final int userInput = UserInputUtil.getUserInput(values.length, names);
        Delete.deleteLastAdd(values[userInput]);
    }

    private List<String> getNamesOfType(final ProductType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (ProductType type : values) {
            names.add(type.name());
        }
        return names;
    }

    public static void deleteLastAdd(ProductType type) {
        switch (type) {
            case PHONE -> PHONE_SERVICE.delete(PHONE_SERVICE.getAll().get(PHONE_SERVICE.getAll().size()-1));
            case TABLET -> TABLET_SERVICE.delete(TABLET_SERVICE.getAll().get(TABLET_SERVICE.getAll().size()-1));
            case TELEVISION -> TV_SERVICE.delete(TV_SERVICE.getAll().get(TV_SERVICE.getAll().size()-1));
            default -> throw new IllegalArgumentException("Unknown Product type: " + type);
        };
    }
}
