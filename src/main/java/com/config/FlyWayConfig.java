package com.config;

import org.flywaydb.core.Flyway;

public class FlyWayConfig {
    private static final String URL = "jdbc:postgresql://ec2-3-229-165-146.compute-1.amazonaws.com:5432/dm9mag068fkst";
    private static final String USER = "qgugetlsfqprzg";
    private static final String PASSWORD = "051fc287951c8d95b5ffe973fb46fddc6516742e74d2d9f02654c1c5011829c9";
    private static final String SCHEMA = "public";
    private static final String LOCATION = "db/migration";

    private FlyWayConfig() {
    }

    public static Flyway getInstance() {
        return Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .baselineOnMigrate(true)
                .schemas(SCHEMA)
                .locations(LOCATION)
                .load();
    }
}
