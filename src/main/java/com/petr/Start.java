package com.petr;

import com.petr.db.migration.DatabaseMigration;
import com.petr.panel.ApiRequests;
import com.petr.panel.ApiRequestsGermImpl;

import java.io.IOException;
import java.util.UUID;

public class Start {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatabaseMigration.startMigration(args[0]);
    }
}
