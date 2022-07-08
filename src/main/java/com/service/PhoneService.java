package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.PhoneRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PhoneService {
    private static final Random RANDOM = new Random();

    private final PhoneRepository repository;

    public PhoneService(PhoneRepository repository) {
        this.repository = repository;
    }

    public void createAndSavePhones(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count must been bigger then 0");
        }
        List<Phone> phones = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Phone phone = new Phone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()
            );
            phones.add(phone);
        }
        repository.saveAll(phones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Phone phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public void deletePhoneById(String id){
        repository.delete(id);
    }

    public void updatePhone(Phone phone){
        repository.update(phone);
    }
    public void savePhone(Phone phone) {
        if (phone.getCount() == 0) {
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    public List<Phone> getAll() {
        return repository.getAll();
    }
}
