package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Tablet;
import com.repository.CrudRepository;
import com.repository.TabletRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TabletService extends ProductService<Tablet> {

    public TabletService(CrudRepository repository) {
        super(repository);
    }

    @Override
    protected Tablet creatProduct() {
        return new Tablet(
                Tablet.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
