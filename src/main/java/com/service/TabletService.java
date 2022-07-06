package com.service;

import com.model.Manufacturer;
import com.model.Tablet;
import com.repository.TabletRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TabletService {
    private static final Random RANDOM = new Random();
    private static final TabletRepository REPOSITORY = new TabletRepository();

    public void createAndSaveTablet(int count) {
        List<Tablet> tablets = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            tablets.add(new Tablet(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            ));
        }
        REPOSITORY.saveAll(tablets);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Tablet tablet : REPOSITORY.getAll()) {
            System.out.println(tablet);
        }
    }
}
