package com.service;

import com.model.Manufacturer;
import com.model.Television;
import com.repository.TelevisionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TelevisionService {
    private static final Random RANDOM = new Random();
    private static final TelevisionRepository REPOSITORY = new TelevisionRepository();

    public void createAndSaveTelevision(int count) {
        List<Television> televisions = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            televisions.add(new Television(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(2000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            ));
        }
        REPOSITORY.saveAll(televisions);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Television television : REPOSITORY.getAll()) {
            System.out.println(television);
        }
    }
}
