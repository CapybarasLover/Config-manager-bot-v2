package com.petr;

import com.petr.panel.ApiRequests;
import com.petr.panel.ApiRequestsImpl;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Bot bot = new Bot();
//        bot.startBot();
        ApiRequests api = new ApiRequestsImpl();
        System.out.println(api.getAllConfigsRequest());
    }
}
