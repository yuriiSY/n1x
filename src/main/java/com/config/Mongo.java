package com.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Mongo {

    private static MongoClient mongo ;
    private static MongoDatabase database ;
    private static String s = "n1x";

    public static MongoDatabase getDatabase() {
        mongo = new MongoClient( "localhost" , 27017 );
        database = mongo.getDatabase(s);
        return database;
    }
}
