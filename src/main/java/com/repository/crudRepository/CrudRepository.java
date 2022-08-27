package com.repository.crudRepository;

import com.model.Phone;

import java.util.List;
import java.util.Optional;

public interface CrudRepository <T> {

    void save(T product);

    void saveAll(List<T> product);

    boolean update(T product);

    boolean delete(String id);

    List<T> getAll();

    Optional<T> findById(String id);
    Optional<T> findByTitle(String title);
}
