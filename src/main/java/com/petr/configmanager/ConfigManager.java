package com.petr.configmanager;

import io.github.natanimn.telebof.BotContext;
import io.github.natanimn.telebof.types.updates.Message;

import java.io.IOException;

public interface ConfigManager {
    String getAllConfigs() throws IOException, InterruptedException;
    String[] getConfigs(Long userId, String username) throws IOException, InterruptedException;
    String[] getConfigs(Long userId) throws IOException, InterruptedException;
    String deleteConfig(Long userId) throws IOException, InterruptedException;
    String onStart(Long userId, String username);
    String getWaitingConfigs();
    String acceptConfig(Long userId);
    boolean isRegistered(long userId);
    String getUsernameById(Long userId);
}
