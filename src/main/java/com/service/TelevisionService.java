package com.service;

import com.model.Details;
import com.model.Manufacturer;
import com.model.Phone;
import com.model.Television;
import com.repository.CrudRepository;
import com.repository.TabletRepository;
import com.repository.TelevisionRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
                getRandomManufacturer(),
                detailsCreate()
        );
    }

    private List<String> detailsCreate(){
        List<String> details = Arrays.asList("DISPLAY","SPEAKERS","RAM","STAND");
        List<String> det = Arrays.asList(details.get(RANDOM.nextInt(details.size())),details.get(RANDOM.nextInt(details.size())));
        return det;
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean findDetail(String detail) {
        return repository.getAll().stream().flatMap(tv -> tv.getDetails().stream())
                .anyMatch(d -> d.equals(detail));
    }


    private Television productFromMap(Map<String, Object> map) {
        Function<Map<String,Object>,Television> function = (m) -> {
            Television television = new Television((String) m.get("title"),
                    (Integer) m.get("count"),
                    (Integer) m.get("price"),
                    (String) m.get("model"),
                    Manufacturer.APPLE,
                    detailsCreate());
            return television;
        };
        return function.apply(map);
    }
}

