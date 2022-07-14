package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Product;
import com.repository.PhoneRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class OptionalService {

    private final PhoneRepository repository;

    private static final Random RANDOM = new Random();
    public OptionalService(PhoneRepository repository) {
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
    public List<Phone> getAll() {
        return repository.getAll();
    }
    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public void printIfPresent(String id) {
        final Optional<Phone> phoneOptional = repository.findById(id);
        phoneOptional.ifPresent(phone -> { System.out.println(phone); } );
    }

    public Phone printOrGetDefault(String id) {
        final Phone phone = repository.findById(id)
                .orElse(createAndSavePhone());
        return phone;
    }

    public Phone printOrCreatDefault(String id) {
        final Phone phone = repository.findById(id)
                .orElseGet(() -> createAndSavePhone());
        return phone;
    }

    public String mapPhoneToString(String id) {
        final String phone1 = repository.findById(id)
                .map(p -> p.toString())
                .orElse("Phone not found");
        return phone1;
    }
//
    public void printOrPrintDefault(String id) {
        repository.findById(id).ifPresentOrElse(
                phone -> {
                    System.out.println(phone);
                },
                () -> {
                    System.out.println(createAndSavePhone());
                }
        );
    }

    public void checksPhoneLessThen(String id, int count) {
        repository.findById(id)
                .filter(phone -> phone.getCount() <= count)
                .ifPresentOrElse(
                        phone -> {
                            System.out.println(phone);
                        },
                        () -> {
                            System.out.println("Phone with count " + count + " not found");
                        }
                );
    }

    public Phone printPhoneOrElseThrowException(String id) {
        final Phone phone = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone with id " + id + " not found"));
        return phone;
    }

    public void printPhone(String id) {
        repository.findById(id).or(() -> Optional.of(createAndSavePhone()))
                .ifPresent(phone -> System.out.println(phone));
    }

    private Phone createAndSavePhone() {
        return new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
    }


}
