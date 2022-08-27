package com.repository.simplRepository;

import com.annotations.MyAutowired;
import com.annotations.MySingleton;
import com.model.Phone;
import com.repository.crudRepository.CrudRepository;

import java.util.*;

@MySingleton
public class PhoneRepository implements CrudRepository<Phone> {
    private final List<Phone> phones;
    private static PhoneRepository instance;
    @MyAutowired
    private PhoneRepository() {
        phones = new LinkedList<>();
    }

    public static PhoneRepository getInstance() {
        if (instance == null) {
            instance = new PhoneRepository();
        }
        return instance;
    }


    @Override
    public void save(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException();
        } else {
            phones.add(phone);
        }
    }

    @Override
    public void saveAll(List<Phone> phones) {
        for (Phone phone : phones) {
            save(phone);
        }
    }

    @Override
    public boolean update(Phone phone) {
        final Optional<Phone> result = findById(phone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Phone originPhone = result.get();
        PhoneCopy.copy(phone, originPhone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Phone> iterator = phones.iterator();
        while (iterator.hasNext()) {
            final Phone phone = iterator.next();
            if (phone.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Phone> getAll() {
        if (phones.isEmpty()) {
            return Collections.emptyList();
        }
        return phones;
    }

    @Override
    public Optional<Phone> findById(String id) {
        Phone result = null;
        for (Phone phone : phones) {
            if (phone.getId().equals(id)) {
                result = phone;
            }
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Phone> findByTitle(String title) {
        Phone result = null;
        for (Phone phone : phones) {
            if (phone.getTitle().equals(title)) {
                result = phone;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class PhoneCopy {
        private static void copy(final Phone from, final Phone to) {
            to.setCount(0);
            to.setPrice(0);
            to.setTitle("New Title");
        }
    }
}
