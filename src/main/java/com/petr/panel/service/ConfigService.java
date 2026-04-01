package com.petr.panel.service;

public interface ConfigService {
    String createVlessLink(String uuid, String name);
    String createSubLink(String subUuid);
    String listClients();
    String deleteClient(String clientId);
}
