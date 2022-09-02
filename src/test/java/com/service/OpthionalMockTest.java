package com.service;

import com.model.product.Manufacturer;
import com.model.phone.Phone;
import com.repository.simplRepository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class OpthionalMockTest {

    private OptionalService target;
    private PhoneRepository repository;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(PhoneRepository.class);
        target = new OptionalService(repository);
    }

    @Test
    void printIfPresent() {
        Phone phone = creatSimplePhone();
        Mockito.when(repository.findById("")).thenReturn(Optional.of(phone));
        Phone actual = target.printIfPresent("");
        Assertions.assertEquals(phone.getId(), actual.getId());
    }
    private Phone creatSimplePhone() {
        return new Phone("Title", 10, 1000.0, "Model", Manufacturer.APPLE);
    }
    @Test
    void printIfNotPresent() {
        Mockito.when(repository.findById("")).thenReturn(Optional.empty());
        Phone actual = target.printIfPresent("");
        Assertions.assertEquals(null, actual);
    }

    @Test
    void printOrPrintDefault() {
        Phone phone = creatSimplePhone();
        Mockito.when(repository.findById("")).thenReturn(Optional.of(phone));
        Phone actual = target.printOrPrintDefault("");
        Assertions.assertEquals(phone.getId(),actual.getId());
    }

    @Test
    void checksPhoneLessThen_shouldFind() {
        Phone phone = creatSimplePhone();
        Mockito.when(repository.findById("")).thenReturn(Optional.of(phone));
        String actual = target.checksPhoneLessThen(Mockito.anyString(),10);
        Assertions.assertEquals(phone.toString(),actual);

    }
    @Test
    void checksPhoneLessThen_shouldNotFound() {
        Phone phone = creatSimplePhone();
        Mockito.when(repository.findById("")).thenReturn(Optional.of(phone));
        String actual = target.checksPhoneLessThen(Mockito.anyString(),2);
        Assertions.assertEquals("Phone with count 2 not found",actual);

    }

    @Test
    void printPhone() {
        target.printPhone(Mockito.anyString());
        Mockito.verify(repository).findById(Mockito.anyString());
    }
}
