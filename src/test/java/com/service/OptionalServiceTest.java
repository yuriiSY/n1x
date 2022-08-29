package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.repository.simplRepository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OptionalServiceTest {

    private static final OptionalService OPTIONAL_EXAMPLES = new OptionalService(PhoneRepository.getInstance());

    @BeforeEach
    void setUp(){
        OPTIONAL_EXAMPLES.createAndSavePhones(2);
    }

    @Test
    void printOrGetDefault_shouldReturnPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Assertions.assertEquals(OPTIONAL_EXAMPLES.getAll().get(0),OPTIONAL_EXAMPLES.printOrGetDefault(id));
    }

    @Test
    void printOrGetDefault_shouldReturnNewPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Phone phone = new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
        Assertions.assertEquals(phone.getModel(),OPTIONAL_EXAMPLES.printOrGetDefault(phone.getId()).getModel());
        Assertions.assertEquals(phone.getTitle(),OPTIONAL_EXAMPLES.printOrGetDefault(phone.getId()).getTitle());
        Assertions.assertEquals(phone.getManufacturer(),OPTIONAL_EXAMPLES.printOrGetDefault(phone.getId()).getManufacturer());
        Assertions.assertEquals(phone.getPrice(),OPTIONAL_EXAMPLES.printOrGetDefault(phone.getId()).getPrice());
    }

    @Test
    void printOrCreatDefault_shouldReturnPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Assertions.assertEquals(OPTIONAL_EXAMPLES.getAll().get(0),OPTIONAL_EXAMPLES.printOrCreatDefault(id));
    }

    @Test
    void printOrCreatDefault_shouldReturnNewPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Phone phone = new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
        Assertions.assertEquals(phone.getModel(),OPTIONAL_EXAMPLES.printOrCreatDefault(phone.getId()).getModel());
        Assertions.assertEquals(phone.getTitle(),OPTIONAL_EXAMPLES.printOrCreatDefault(phone.getId()).getTitle());
        Assertions.assertEquals(phone.getManufacturer(),OPTIONAL_EXAMPLES.printOrCreatDefault(phone.getId()).getManufacturer());
        Assertions.assertEquals(phone.getPrice(),OPTIONAL_EXAMPLES.printOrCreatDefault(phone.getId()).getPrice());
    }

    @Test
    void mapPhoneToString_shouldReturnPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Assertions.assertEquals(String.format("Phone: "+OPTIONAL_EXAMPLES.getAll().get(0).toString()),OPTIONAL_EXAMPLES.mapPhoneToString(id));
    }

    @Test
    void mapPhoneToString_shouldReturnSting() {
        String s = "Phone not found";
        Assertions.assertEquals(s,OPTIONAL_EXAMPLES.mapPhoneToString("asd"));
    }

    @Test
    void printPhoneOrElseThrowException_shouldReturnPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Assertions.assertEquals(OPTIONAL_EXAMPLES.getAll().get(0),OPTIONAL_EXAMPLES.printPhoneOrElseThrowException(id));
    }

    @Test
    void printPhoneOrElseThrowException_shouldReturnNewPhone() {
        String id = OPTIONAL_EXAMPLES.getAll().get(0).getId();
        Assertions.assertThrows(IllegalArgumentException.class, () ->  OPTIONAL_EXAMPLES.printPhoneOrElseThrowException("sd"));
    }

}