package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;

public class PhoneService extends ProductService<Phone> {

    private final PhoneRepository repository;
    private static PhoneService instance;

    private PhoneService(final PhoneRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public static PhoneService getInstance() {
        if (instance == null) {
            instance = new PhoneService(PhoneRepository.getInstance());
        }
        return instance;
    }

    public static PhoneService getInstance(final PhoneRepository repository) {
        if (instance == null) {
            instance = new PhoneService(repository);
        }
        return instance;
    }
    @Override
    protected Phone creatProduct() {
        return new Phone(
                Phone.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
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
