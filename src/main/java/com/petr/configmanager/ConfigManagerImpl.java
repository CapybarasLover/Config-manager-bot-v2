package com.petr.configmanager;

import com.petr.panel.service.ConfigService;
import com.petr.panel.service.ConfigServiceGermImpl;

public class ConfigManagerImpl implements ConfigManager {
    private final ConfigService configServiceGerm = new ConfigServiceGermImpl();

    @Override
    public String getAllConfigs() {
        return configServiceGerm.listClients();
    }

    //TODO по хорошему написать так же попытки получить запросом с панели, с локальной бд и возврат null или пустой строки
    @Override
    public String getClient(String clientName) {
        return ""; //TODO эта функция вызывается всегда - возвращает либо клиент из локальной бд либо null
    }

    @Override
    public String createClient(long tgId, String clientName) {
        return "";
    }

    @Override
    public String deleteClient(String clientName) {
        return "";
    }
}
