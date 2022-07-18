package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Television;
import com.repository.CrudRepository;

public class TelevisionService extends ProductService<Television> {

    public TelevisionService(CrudRepository repository) {
        super(repository);
    }

    @Override
    protected Television creatProduct() {
        return new Television(
                Television.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
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

