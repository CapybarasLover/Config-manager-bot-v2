package com.petr;

import com.petr.panel.ApiRequests;
import com.petr.panel.ApiRequestsGermImpl;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Bot bot = new Bot();
//        bot.startBot();
        ApiRequests api = new ApiRequestsGermImpl();
        System.out.println(api.getAllConfigsRequest().body());
    }
}
