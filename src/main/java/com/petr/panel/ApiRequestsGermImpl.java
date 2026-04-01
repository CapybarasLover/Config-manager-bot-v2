package com.petr.panel;

import com.petr.exception.LoginException;
import com.petr.exception.RetryAttemptsLeftException;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ApiRequestsGermImpl implements ApiRequests {
    private final int MAX_RETRIES = 2;
    @FunctionalInterface
    private interface ThrowingSupplier<T> {
        T get() throws Exception;
    }

    private final CookieManager cookies = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
    private final HttpClient client = HttpClient.newBuilder().cookieHandler(cookies).build();
    private URI baseUri = URI.create(System.getenv("GERMAN_PANEL_HOME_URL"));
    private final String settingsTemplate = "{\"clients\": [{ " +
            "\"id\": \"%s\", " +
            "\"flow\": \"xtls-rprx-vision\", " +
            "\"email\": \"%s\", " +
            "\"limitIp\": 0, " +
            "\"totalGB\": 0, " +
            "\"expiryTime\": 0, " +
            "\"enable\": true, " +
            "\"tgId\": \"%s\", " +
            "\"subId\": \"%s\", " +
            "\"comment\": \"created from tg bot\", " +
            "\"reset\": 0 }]}";
//
//    public void ApiRequests() throws IOException, InterruptedException {
//        login();
//    }
    // TODO Раскомментить!!!!!!!!!!!!! после дебага
    private <T> T retry(ThrowingSupplier<T> requestToRetry){
        int attempts = 0;

        while(attempts < MAX_RETRIES){
            try {
                login();
                return requestToRetry.get();
            } catch(LoginException loginEx){
                System.out.println(loginEx.getMessage() + loginEx.getStatusCode());
            } catch(Exception ex) {
                System.out.println("Unhandled exception");
            } finally {
                attempts++;
            }
        }
        throw new RetryAttemptsLeftException("To many attempts",  MAX_RETRIES);
    }

    private void login() throws LoginException, IOException, InterruptedException {
        String username = System.getenv("XUI_USERNAME");
        String password = System.getenv("XUI_PASSWORD");

        URI loginUri = baseUri.resolve("login");
        String body = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) +
                "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder(loginUri)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            System.out.println(response.statusCode());
            System.out.println(response.headers());
            System.out.println(response.body());
            System.out.println(response);
            throw new LoginException("Login failed: ", response.statusCode());
        }
        else{
            System.out.println(response.statusCode());
            System.out.println(response.headers());
            System.out.println(response.body());
        }
    }

    @Override
    public String addClientRequest(String inboundId, UUID uuid, UUID subUuid, String name) {

        return "";
    }

    @Override
    public HttpResponse<String> getAllConfigsRequest() throws IOException, InterruptedException {
        URI getConfigsUrl = baseUri.resolve("panel/api/inbounds/list");
        System.out.println(getConfigsUrl);
        HttpRequest request = HttpRequest.newBuilder(getConfigsUrl)
                .GET()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 404){
            return retry(this::getAllConfigsRequest);
        }

        return response;
    }

    @Override
    public String deleteClient(String inboundId, String name) {
        return "";
    }
}
