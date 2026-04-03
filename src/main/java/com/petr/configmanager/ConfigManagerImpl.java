package com.petr.configmanager;

import com.petr.panel.service.ConfigService;
import com.petr.panel.service.ConfigServiceGermImpl;

public class ConfigManagerImpl implements ConfigManager {
    private final ConfigService configServiceGerm = new ConfigServiceGermImpl();
}
