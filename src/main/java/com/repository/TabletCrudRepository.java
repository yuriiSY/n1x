package com.repository;

import com.model.Tablet;

import java.util.List;
import java.util.Optional;

public interface TabletCrudRepository {

    void save(Tablet tablet);

    void saveAll(List<Tablet> tablets);

    boolean update(Tablet tablet);

    boolean delete(String id);

    List<Tablet> getAll();

    Optional<Tablet> findById(String id);
}
