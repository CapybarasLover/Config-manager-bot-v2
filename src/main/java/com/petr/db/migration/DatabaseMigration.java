package com.petr.db.dao.migration;

public class DatabaseMigration {

    public static void startMigration(String execParam){
        if(execParam.equals("prod")){
            String dbLink = System.getenv("DB_LINK_PROD");
        } else {
            String dbLink = System.getenv("DB_LINK_TEST");
        }

        String name = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");


    }
}
