package com.petr.db.migration;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseMigration {

    public static void startMigration(String execParam){
        String dbLink;
        if(execParam.equals("prod")){
            dbLink = System.getenv("DB_LINK_PROD");
        } else {
            dbLink = System.getenv("DB_LINK_TEST");
        }

        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection(dbLink, username, password)) {

            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            try (Liquibase liquibase = new Liquibase(
                    "db/db.changelog-master.yaml",
                    new ClassLoaderResourceAccessor(),
                    database)) {

                liquibase.update("");

                System.out.println("Liquibase: миграции успешно применены!");
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при запуске Liquibase", e);
        }
    }
}
