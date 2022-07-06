package com.repository;

import com.model.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneCrudRepository {
    void save(Phone product);

    void saveAll(List<Phone> phones);

    boolean update(Phone phone);

    boolean delete(String id);

    List<Phone> getAll();

    Optional<Phone> findById(String id);
    Optional<Phone> findByTitle(String title);
}
