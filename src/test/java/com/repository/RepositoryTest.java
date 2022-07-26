package com.repository;

import com.model.Manufacturer;
import com.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private PhoneRepository target;
    private Phone phone;

    @BeforeEach
    void setUp() {
        final Random random = new Random();
        target = PhoneRepository.getInstance();
        phone = new Phone(
                "Title-" + random.nextInt(1000),
                random.nextInt(500),
                random.nextDouble(1000.0),
                "Model-" + random.nextInt(10),
                Manufacturer.APPLE
        );
    }

    @Test
    void save() {
        target.save(phone);
        final List<Phone> phones = target.getAll();
        Assertions.assertEquals(1, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
    }

    @Test
    void save_putNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void saveAll_singlePhone() {
        target.saveAll(Collections.singletonList(phone));
        final List<Phone> phones = target.getAll();
        Assertions.assertEquals(1, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
    }

    @Test
    void saveAll_noPhone() {
        target.saveAll(Collections.emptyList());
        final List<Phone> phones = target.getAll();
        Assertions.assertEquals(0, phones.size());
    }

    @Test
    void saveAll_manyPhones() {
        final Phone otherPhone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        target.saveAll(List.of(phone, otherPhone));
        final List<Phone> phones = target.getAll();
        Assertions.assertEquals(2, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
        Assertions.assertEquals(phones.get(1).getId(), otherPhone.getId());
    }

    @Test
    void saveAll_hasNull() {
        final List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        phones.add(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.saveAll(phones));
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }

    @Test
    void update() {
        final String newTitle = "New title";
        target.save(phone);
        phone.setTitle(newTitle);
        final boolean result = target.update(phone);
        Assertions.assertTrue(result);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
        Assertions.assertEquals(newTitle, actualResult.get(0).getTitle());
        Assertions.assertEquals(phone.getId(), actualResult.get(0).getId());
        Assertions.assertEquals(phone.getCount(), actualResult.get(0).getCount());
    }

    @Test
    void update_noPhone() {
        target.save(phone);
        final Phone noPhone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        final boolean result = target.update(noPhone);

        Assertions.assertFalse(result);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
        Assertions.assertEquals(phone.getId(), actualResult.get(0).getId());
        Assertions.assertEquals(phone.getCount(), actualResult.get(0).getCount());
    }

    @Test
    void delete() {
        target.save(phone);
        final boolean result = target.delete(phone.getId());
        Assertions.assertTrue(result);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void delete_noPhone() {
        target.save(phone);
        final Phone noPhone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        final boolean result = target.delete(noPhone.getId());
        Assertions.assertFalse(result);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }


    @Test
    void getAll() {
        target.save(phone);
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }

    @Test
    void getAll_noPhones() {
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void findById() {
        target.save(phone);
        final Optional<Phone> optionalPhone = target.findById(phone.getId());
        Assertions.assertTrue(optionalPhone.isPresent());
        final Phone actualPhone = optionalPhone.get();
        Assertions.assertEquals(phone.getId(), actualPhone.getId());
    }
}