package com.petr.configmanager;

public interface ConfigManager {
    String getAllConfigs();
    String getClient(String clientName);
    String createClient(long tgId, String clientName);
    String deleteClient(String clientName);
}
