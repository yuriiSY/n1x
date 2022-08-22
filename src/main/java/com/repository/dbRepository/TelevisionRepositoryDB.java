package com.repository.dbRepository;

import com.config.JDBCConfig;
import com.model.Manufacturer;
import com.model.Tablet;
import com.model.Television;
import com.repository.CrudRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TelevisionRepositoryDB implements CrudRepository<Television> {

    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static TelevisionRepositoryDB instance;

    public static TelevisionRepositoryDB getInstance() {
        if (instance == null) {
            instance = new TelevisionRepositoryDB();
        }
        return instance;
    }

    @Override
    public void save(Television television) {
        String sql = "INSERT INTO public.television (id, title, count, manufacturer, price, model) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            setTelevision(statement, television);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void setTelevision(PreparedStatement preparedStatement, Television television) {
        try {
            preparedStatement.setString(1, television.getId());
            preparedStatement.setString(2, television.getTitle());
            preparedStatement.setInt(3, television.getCount());
            preparedStatement.setString(4, television.getManufacturer().name());
            preparedStatement.setDouble(5, television.getPrice());
            preparedStatement.setString(6, television.getModel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveAll(List<Television> televisions) {
        String sql = "INSERT INTO public.television (id, title, count, manufacturer, price, model) VALUES (?, ?, ?, ?, ?, ?)";

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            CONNECTION.setAutoCommit(false);
            for (Television television : televisions ) {
                setTelevision(statement, television);
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
    public boolean update(Television television) {
        String sql = "UPDATE public.television SET title = ?,count = ?, price = ?, manufacturer = ?, model = ?  WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            setTelevisionForUpdate(preparedStatement,television);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private static void setTelevisionForUpdate(PreparedStatement preparedStatement,Television television) {
        try {
            preparedStatement.setString(1, television.getTitle());
            preparedStatement.setInt(2, television.getCount());
            preparedStatement.setDouble(3, television.getPrice());
            preparedStatement.setString(4, television.getManufacturer().toString());
            preparedStatement.setString(5, television.getModel());
            preparedStatement.setString(6, television.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM public.television WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Television> getAll() {
        final List<Television> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM public.television");
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @SneakyThrows
    private Television setFieldsToObject(final ResultSet resultSet) {
        final String model = resultSet.getString("model");
        final String title = resultSet.getString("title");
        final double price = resultSet.getDouble("price");
        final int count = resultSet.getInt("count");
        final Manufacturer manufacturer = EnumUtils.getEnum(Manufacturer.class, resultSet.getString("manufacturer"),
                Manufacturer.NONE);
        final Television television = new Television(title, count, price, model, manufacturer);
        television.setId(resultSet.getString("id"));
        return television;
    }
    @Override
    public Optional<Television> findById(String id) {
        String sql = "SELECT * FROM public.tablet WHERE id = ?";
        Optional<Television> television = Optional.empty();

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                television = Optional.of(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return television;
    }

    @Override
    public Optional<Television> findByTitle(String title) {
        String sql = "SELECT * FROM public.television WHERE title = ?";

        Optional<Television> television = Optional.empty();

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, title);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                television = Optional.of(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return television;
    }
}
