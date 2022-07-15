package com.service;

import com.model.Phone;
import com.repository.PhoneRepository;
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
      target.printIfPresent(Mockito.anyString());
      Mockito.verify(repository).findById(Mockito.anyString());
    }

    @Test
    void printOrPrintDefault() {
        target.printOrGetDefault(Mockito.anyString());
        Mockito.verify(repository).findById(Mockito.anyString());
    }

    @Test
    void checksPhoneLessThen() {
        target.checksPhoneLessThen(Mockito.anyString(),2);
        Mockito.verify(repository).findById(Mockito.anyString());
    }

    @Test
    void printPhone() {
        target.printPhone(Mockito.anyString());
        Mockito.verify(repository).findById(Mockito.anyString());
    }
}
