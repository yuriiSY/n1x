package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Tablet;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;
import com.repository.TabletRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class TabletService extends ProductService<Tablet> {

    private final TabletRepository repository;
    private static TabletService instance;

    private TabletService(final TabletRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public static TabletService getInstance() {
        if (instance == null) {
            instance = new TabletService(TabletRepository.getInstance());
        }
        return instance;
    }

    public static TabletService getInstance(final TabletRepository repository) {
        if (instance == null) {
            instance = new TabletService(repository);
        }
        return instance;
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

    @Override
    public Tablet productFromMap(Map<String, Object> map) {
        Function<Map<String,Object>,Tablet> function = (m) -> {
            Tablet tablet  = new Tablet((String) m.get("title"),
                    (Integer) m.get("count"),
                    (Integer) m.get("price"),
                    (String) m.get("model"),
                    Manufacturer.APPLE);
            return tablet;
        };
        return function.apply(map);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
