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
//??????
   /* @Test
    void printOrPrintDefault() {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(null);
        target.printOrGetDefault(Mockito.anyString());
        Mockito.verify(repository).findById(Mockito.anyString()).orElseGet(Mockito.any());
    }*/

    @Test
    void checksPhoneLessThen() {
        target.checksPhoneLessThen(Mockito.anyString(),Mockito.anyInt());
        Mockito.verify(repository, Mockito.times(2)).findById(Mockito.anyString());
    }

    @Test
    void printPhone() {
    }
}
