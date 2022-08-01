package com.service;

import com.model.Manufacturer;
import com.model.OperationSystem;
import com.model.Phone;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                    Integer.parseInt((String) m.get("count")),
                    Integer.parseInt((String) m.get("price")),
                    (String) m.get("model"),
                    Manufacturer.APPLE,
                    new OperationSystem((String) m.get("designation"), Integer.parseInt((String) m.get("version"))),
                    LocalDateTime.parse((CharSequence) m.get("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
                    );
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
