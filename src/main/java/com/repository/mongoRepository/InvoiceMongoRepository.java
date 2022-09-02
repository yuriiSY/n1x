package com.repository.mongoRepository;

import com.config.Mongo;
import com.google.gson.*;
import com.model.invoice.Invoice;
import com.model.phone.Phone;
import com.model.product.Product;
import com.model.tablet.Tablet;
import com.model.television.Television;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.repository.crudRepository.CrudRepository;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.persistence.Table;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public class InvoiceMongoRepository implements CrudRepository<Invoice> {

    private final MongoCollection<Document> collection;

    private final Gson gson;

    public InvoiceMongoRepository() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsString() + " 00:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withLocale(Locale.ENGLISH)))
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create();
        collection = Mongo.getDatabase().getCollection("invoice");


    }

    @Override
    public void save(Invoice product) {
        collection.insertOne(Document.parse(gson.toJson(product)));
    }

    @Override
    public void saveAll(List<Invoice> products) {
        List<Document> documents = new ArrayList<>();
        for (Invoice invoice : products) {
            documents.add(Document.parse(gson.toJson(invoice)));
        }
        collection.insertMany(documents);
    }

    @Override
    public boolean update(Invoice product) {
        Document filter = new Document();
        filter.append("id",product.getId());
        Document newData = new Document();
        newData.append("id",product.getId());
        Document updateData = new Document();
        updateData.append("$set",newData);
        collection.updateOne(filter,updateData);
        return true;
    }

    @Override
    public boolean delete(String id) {
        Document filter = new Document();
        filter.append("id",id);
        collection.deleteOne(filter);
        return true;
    }

    @Override
    public List<Invoice> getAll() {
        return collection.find().map(document -> gson.fromJson(document.toJson(),Invoice.class)).into(new ArrayList<>());
    }

    @Override
    public Optional<Invoice> findById(String id) {
        Document filter = new Document();
        filter.append("id",id);
        return Optional.ofNullable(collection.find(filter).map(document -> gson.fromJson(document.toJson(),Invoice.class)).first());
    }

    @Override
    public Optional<Invoice> findByTitle(String title) {
        Document filter = new Document();
        filter.append("title",title);
        return Optional.ofNullable(collection.find(filter).map(document -> gson.fromJson(document.toJson(),Invoice.class)).first());
    }

    public long getCountOfInvoice() {
        return collection.countDocuments();
    }

    public List<Invoice> moreThanX(double x) {
        Bson filter = Filters.gte("sum", x);
        return collection.find(filter).map(document -> gson.fromJson(document.toJson(), Invoice.class)).into(new ArrayList<>());
    }


    public Map<Double, Long> groupBy() {

        Map<Double, Long> map = new HashMap<>();

        collection.aggregate(List.of(Aggregates.group("$sum", Accumulators.sum("count", 1))))
                .map(document -> gson.fromJson(document.toJson(), JsonObject.class))
                .forEach((Consumer<? super JsonObject>) jsonObject -> {
                    double sum = jsonObject.get("_id").getAsDouble();
                    long count = jsonObject.get("count").getAsLong();
                    map.put(sum, count);
                });
        return map;

    }

}
