package com.repository.dbRepository;

import com.config.JDBCConfig;
import com.model.Manufacturer;
import com.model.Phone;
import com.repository.CrudRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneRepositoryDB implements CrudRepository<Phone> {
    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static PhoneRepositoryDB instance;

    public static PhoneRepositoryDB getInstance() {
        if (instance == null) {
            instance = new PhoneRepositoryDB();
        }
        return instance;
    }

    @Override
    public void save(Phone phone) {
        String sql = "INSERT INTO public.phone (id, title, count, manufacturer, price, model) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            setPhoneForInsert(statement, phone);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setPhoneForInsert(PreparedStatement preparedStatement,Phone phone) {
        try {
            preparedStatement.setString(1, phone.getId());
            preparedStatement.setString(2, phone.getTitle());
            preparedStatement.setInt(3, phone.getCount());
            preparedStatement.setString(4, phone.getManufacturer().name());
            preparedStatement.setDouble(5, phone.getPrice());
            preparedStatement.setString(6, phone.getModel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(List<Phone> phones) {
        String sql = "INSERT INTO public.phone (id, title, count, manufacturer, price, model) VALUES (?, ?, ?, ?, ?, ?)";

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            CONNECTION.setAutoCommit(false);
            for (Phone phone : phones) {
                setPhoneForInsert(statement, phone);
                statement.addBatch();
            }
            statement.executeBatch();
            CONNECTION.commit();
            CONNECTION.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Phone phone) {
        String sql = "UPDATE public.phone SET title = ?,count = ?, price = ?, manufacturer = ?, model = ?  WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            setPhoneForUpdate(preparedStatement,phone);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    private static void setPhoneForUpdate(PreparedStatement preparedStatement,Phone phone) {
        try {
            preparedStatement.setString(1, phone.getTitle());
            preparedStatement.setInt(2, phone.getCount());
            preparedStatement.setDouble(3, phone.getPrice());
            preparedStatement.setString(4, phone.getManufacturer().toString());
            preparedStatement.setString(5, phone.getModel());
            preparedStatement.setString(6, phone.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM public.phone WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Phone> getAll() {
        final List<Phone> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM public.phone");
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    @Override
    public Optional<Phone> findById(String id) {
        String sql = "SELECT * FROM public.phone WHERE id = ?";
        Optional<Phone> phone = Optional.empty();

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                phone = Optional.of(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phone;
    }
    @SneakyThrows
    private Phone setFieldsToObject(final ResultSet resultSet) {
        final String model = resultSet.getString("model");
        final String title = resultSet.getString("title");
        final double price = resultSet.getDouble("price");
        final int count = resultSet.getInt("count");
        final Manufacturer manufacturer = EnumUtils.getEnum(Manufacturer.class, resultSet.getString("manufacturer"),
                Manufacturer.NONE);
        final Phone phone = new Phone(title, count, price, model, manufacturer);
        phone.setId(resultSet.getString("id"));
        return phone;
    }

    @Override
    public Optional<Phone> findByTitle(String title) {
        String sql = "SELECT * FROM public.phone WHERE title = ?";

        Optional<Phone> phone = Optional.empty();

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, title);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                phone = Optional.of(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phone;
    }
}
