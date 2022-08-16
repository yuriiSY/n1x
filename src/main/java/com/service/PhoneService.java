package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    @Override
    public Phone productFromMap(Map<String,Object> map){
        Function<Map<String,Object>,Phone> function = (m) -> {
            Phone phone = new Phone((String) m.get("title"),
                    (Integer) m.get("count"),
                    (Integer) m.get("price"),
                    (String) m.get("model"),
                    Manufacturer.APPLE);
            return phone;
        };
        return function.apply(map);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

}
