package com.repository.dbRepository;

import com.config.JDBCConfig;
import com.model.product.Manufacturer;
import com.model.tablet.Tablet;
import com.repository.crudRepository.CrudRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TabletRepositoryDB implements CrudRepository<Tablet> {

    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static TabletRepositoryDB instance;

    public static TabletRepositoryDB getInstance() {
        if (instance == null) {
            instance = new TabletRepositoryDB();
        }
        return instance;
    }

    @Override
    public void save(Tablet tablet) {
        String sql = "INSERT INTO public.tablet (id, title, count, manufacturer, price, model) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            setTablet(statement, tablet);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setTablet(PreparedStatement preparedStatement, Tablet tablet) {
        try {
            preparedStatement.setString(1, tablet.getId());
            preparedStatement.setString(2, tablet.getTitle());
            preparedStatement.setInt(3, tablet.getCount());
            preparedStatement.setString(4, tablet.getManufacturer().name());
            preparedStatement.setDouble(5, tablet.getPrice());
            preparedStatement.setString(6, tablet.getModel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(List<Tablet> tablets) {
        String sql = "INSERT INTO public.tablet (id, title, count, manufacturer, price, model) VALUES (?, ?, ?, ?, ?, ?)";

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            CONNECTION.setAutoCommit(false);
            for (Tablet tablet : tablets) {
                setTablet(statement, tablet);
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
    public boolean update(Tablet tablet) {
        String sql = "UPDATE public.tablet SET title = ?,count = ?, price = ?, manufacturer = ?, model = ?  WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            setTabletForUpdate(preparedStatement,tablet);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private static void setTabletForUpdate(PreparedStatement preparedStatement,Tablet tablet) {
        try {
            preparedStatement.setString(1, tablet.getTitle());
            preparedStatement.setInt(2, tablet.getCount());
            preparedStatement.setDouble(3, tablet.getPrice());
            preparedStatement.setString(4, tablet.getManufacturer().toString());
            preparedStatement.setString(5, tablet.getModel());
            preparedStatement.setString(6, tablet.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM public.tablet WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tablet> getAll() {
        final List<Tablet> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM public.tablet");
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @SneakyThrows
    private Tablet setFieldsToObject(final ResultSet resultSet) {
        final String model = resultSet.getString("model");
        final String title = resultSet.getString("title");
        final double price = resultSet.getDouble("price");
        final int count = resultSet.getInt("count");
        final Manufacturer manufacturer = EnumUtils.getEnum(Manufacturer.class, resultSet.getString("manufacturer"),
                Manufacturer.NONE);
        final Tablet tablet = new Tablet(title, count, price, model, manufacturer);
        tablet.setId(resultSet.getString("id"));
        return tablet;
    }

    @Override
    public Optional<Tablet> findById(String id) {
        String sql = "SELECT * FROM public.tablet WHERE id = ?";
        Optional<Tablet> tablet = Optional.empty();

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tablet = Optional.of(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tablet;
    }

    @Override
    public Optional<Tablet> findByTitle(String title) {
        String sql = "SELECT * FROM public.tablet WHERE title = ?";

        Optional<Tablet> tablet = Optional.empty();

        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, title);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tablet = Optional.of(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tablet;
    }
}
