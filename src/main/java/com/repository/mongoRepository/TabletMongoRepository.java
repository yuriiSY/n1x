package com.repository.mongoRepository;

import com.config.Mongo;
import com.google.gson.*;
import com.model.phone.Phone;
import com.model.tablet.Tablet;
import com.mongodb.client.MongoCollection;
import com.repository.crudRepository.CrudRepository;
import org.bson.Document;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class TabletMongoRepository implements CrudRepository<Tablet> {

    private final MongoCollection<Document> collection;
    private final Gson gson;

    public TabletMongoRepository() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsString() + " 00:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withLocale(Locale.ENGLISH)))
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create();
        collection = Mongo.getDatabase().getCollection("tablet");
    }

    @Override
    public void save(Tablet product) {
        collection.insertOne(Document.parse(gson.toJson(product)));
    }


    @Override
    public void saveAll(List<Tablet> products) {
        List<Document> documents = new ArrayList<>();
        for (Tablet phone : products) {
            documents.add(Document.parse(gson.toJson(phone)));
        }
        collection.insertMany(documents);
    }

    @Override
    public boolean update(Tablet product) {
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
    public List<Tablet> getAll() {
        return collection.find().map(document -> gson.fromJson(document.toJson(),Tablet.class)).into(new ArrayList<>());
    }

    @Override
    public Optional<Tablet> findById(String id) {
        Document filter = new Document();
        filter.append("id",id);
        return Optional.ofNullable(collection.find(filter).map(document -> gson.fromJson(document.toJson(),Tablet.class)).first());
    }

    @Override
    public Optional<Tablet> findByTitle(String title) {
        Document filter = new Document();
        filter.append("title",title);
        return Optional.ofNullable(collection.find(filter).map(document -> gson.fromJson(document.toJson(),Tablet.class)).first());
    }
}
