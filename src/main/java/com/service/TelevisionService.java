package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Television;
import com.repository.CrudRepository;
import com.repository.TabletRepository;
import com.repository.TelevisionRepository;

public class TelevisionService extends ProductService<Television> {


    private final TelevisionRepository repository;
    private static TelevisionService instance;

    private TelevisionService(final TelevisionRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public static TelevisionService getInstance() {
        if (instance == null) {
            instance = new TelevisionService(TelevisionRepository.getInstance());
        }
        return instance;
    }

    public static TelevisionService getInstance(final TelevisionRepository repository) {
        if (instance == null) {
            instance = new TelevisionService(repository);
        }
        return instance;
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

