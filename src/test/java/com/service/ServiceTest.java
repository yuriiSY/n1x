package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private PhoneService target;
    private PhoneRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(PhoneRepository.class);
        target = new PhoneService(repository);
    }

    @Test
    void createAndSavePhones_negativeCount() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->  target.createAndSave(-1));
    }

    @Test
    void createAndSavePhones_zeroCount() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->  target.createAndSave(0));
    }

    @Test
    void createAndSavePhones() {
        target.createAndSave(2);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void getAll_withTimes() {
        target.getAll();
        target.getAll();
        Mockito.verify(repository,Mockito.times(2)).getAll();
    }


    @Test
    void printAll() {
        final Phone phone = new Phone("Title", 100, 1000.0, "Model", Manufacturer.APPLE);
        target.save(phone);
        target.printAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void savePhone() {
        final Phone phone = new Phone("Title", 100, 1000.0, "Model", Manufacturer.APPLE);
        target.save(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
    }

    @Test
    void savePhone_zeroCount() {
        final Phone phone = new Phone("Title", 0, 1000.0, "Model", Manufacturer.APPLE);
        target.save(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
        Assertions.assertEquals(-1, argument.getValue().getCount());
    }

    @Test
    void savePhone_price() {
        final Phone phone = new Phone("Title", 0, 1000.0, "Model", Manufacturer.APPLE);
        target.save(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
        Assertions.assertEquals(-1, argument.getValue().getCount());
        Assertions.assertEquals(1000.0, argument.getValue().getPrice());
    }


    @Test
    void deletePhone_Matcher() {
        final Phone phone = new Phone("Title", 0, 1000.0, "Model", Manufacturer.APPLE);
        target.save(phone);
        Mockito.when(repository.delete(Mockito.argThat(s -> s.length() == 36))).thenReturn(true);
        assertTrue(repository.delete("2b5d48b0-c174-4c08-978f-f64070f4dcdt"));
        assertFalse(repository.delete("asdasda"));
    }

    }