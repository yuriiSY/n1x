package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.simplRepository.PhoneRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

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
    public Phone printIfPresent(String id) {
        AtomicReference s = new AtomicReference();
        final Optional<Phone> phoneOptional = repository.findById(id);
        phoneOptional.ifPresent(phone -> s.set(phone));
        return (Phone) s.get();
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
                .map(p -> String.format("Phone: "  + p.toString()))
                .orElse("Phone not found");
        return phone1;
    }
//
    public Phone printOrPrintDefault(String id) {
        AtomicReference s = new AtomicReference();
        repository.findById(id).ifPresentOrElse(
                phone -> {
                    s.set(phone);
                },
                () -> {
                    s.set(createAndSavePhone());
                }
        );
        return (Phone) s.get();
    }

    public String checksPhoneLessThen(String id, int count) {
        AtomicReference s = new AtomicReference();
        repository.findById(id)
                .filter(phone -> phone.getCount() <= count)
                .ifPresentOrElse(
                        phone -> {
                            s.set(phone.toString());
                        },
                        () -> {
                            s.set("Phone with count " + count + " not found");
                        }
                );
        return (String) s.get();
    }

    public Phone printPhoneOrElseThrowException(String id) {
        final Phone phone = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone with id " + id + " not found"));
        return phone;

    }

    public void printPhone(String id) {
        AtomicReference s = new AtomicReference();
        repository.findById(id).or(() -> Optional.of(createAndSavePhone()))
                .ifPresent(phone -> System.out.println(phone));
    }

    private Phone createAndSavePhone() {
        return new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
    }


}
