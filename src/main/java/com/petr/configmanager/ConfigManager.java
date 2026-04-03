package com.petr.configmanager;

public interface ConfigManager {
    String getAllConfigs();
    String getClient(String clientName);
    String createClient();
}
