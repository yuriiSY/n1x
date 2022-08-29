package com.repository.dbRepository;

import com.config.JDBCConfig;
import com.model.invoice.Invoice;
import com.model.phone.Phone;
import com.model.product.Product;
import com.model.tablet.Tablet;
import com.model.television.Television;
import com.repository.crudRepository.CrudInvoiceRepository;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceRepositoryDB implements CrudInvoiceRepository {

    private static final Connection CONNECTION = JDBCConfig.getConnection();
    private static InvoiceRepositoryDB instance;

    public static InvoiceRepositoryDB getInstance() {
        if (instance == null) {
            instance = new InvoiceRepositoryDB();
        }
        return instance;
    }



    @Override
    public void save(Invoice invoice) {

        String sqlInsert = "INSERT INTO public.invoice (id , sum, time) VALUES (?,?,?)";
        String updatePhone = "UPDATE public.phone SET invoice_id = ? WHERE id = ?";
        String updateTablet = "UPDATE public.tablet SET invoice_id = ? WHERE id = ?";
        String updateTelevision = "UPDATE public.television SET invoice_id = ? WHERE id = ?";

        setInvoice(sqlInsert,invoice);

        for (Product product : invoice.getProducts()) {
            if (product.getClass().equals(Phone.class)) {
                setUpdate(updatePhone,invoice,product);
            }
            if (product.getClass().equals(Television.class)) {
                setUpdate(updateTelevision,invoice,product);
            }
            if (product.getClass().equals(Tablet.class)) {
                setUpdate(updateTablet,invoice,product);
            }
        }
    }

    private void setInvoice(String sqlInsert, Invoice invoice) {
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sqlInsert)) {
            statement.setString(1, invoice.getId());
            statement.setDouble(2, invoice.getSum());
            statement.setDate(3, Date.valueOf((invoice.getTime().toLocalDate())));
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setUpdate(String update,Invoice invoice, Product product) {
        try(final PreparedStatement statement = CONNECTION.prepareStatement(update)) {
            statement.setString(1,invoice.getId());
            statement.setString(2,product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Invoice> moreThanX(double x) {
        return getAll().stream()
                .filter(invoice -> invoice.getSum() > x)
                .toList();
    }

    @Override
    public List<Invoice> getAll() {
        final List<Invoice> result = new ArrayList<>();
        String sql = "SELECT * FROM public.invoice";
        try (final Statement statement = CONNECTION.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int countInvoices() {
        String sql = "SELECT count(id) AS count FROM public.invoice";
        try(Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @SneakyThrows
    private Invoice setFieldsToObject(final ResultSet resultSet) {
        final Invoice invoice = new Invoice();
        invoice.setId(resultSet.getString("id"));
        invoice.setSum(resultSet.getDouble("sum"));
        invoice.setTime(resultSet.getDate("time").toLocalDate().atStartOfDay());
        return invoice;
    }

    @Override
    public void updateInvoiceByID(Invoice invoice) {
        String updateSql = "UPDATE public.invoice SET time = ? WHERE id = ?";
        try(PreparedStatement statement = CONNECTION.prepareStatement(updateSql)) {
            statement.setDate(1, Date.valueOf(invoice.getTime().toLocalDate()));
            statement.setString(2, invoice.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Double, Integer> groupBySum() {
        Map<Double, Integer> count = new HashMap<>();
        String sql = "SELECT sum,count(id) AS count FROM public.invoice GROUP BY sum";
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count.put(resultSet.getDouble("sum"), resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
