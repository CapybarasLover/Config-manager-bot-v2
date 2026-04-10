package com.petr.bot.filters;

import io.github.natanimn.telebof.filters.CustomFilter;
import io.github.natanimn.telebof.types.updates.Update;

public class isAdmin implements CustomFilter {

    @Override
    public boolean check(Update update) {
        if (update.message == null || update.message.text == null) {
            return false;
        }

        String[] adminIdsString = System.getenv("ADMIN_CHATS").split(",");
        Long[] adminChats = new Long[adminIdsString.length];
        for(int i = 0; i < adminIdsString.length; i++){
             if(update.message.chat.id == Long.parseLong(adminIdsString[i])){
                 return true;
             }
        }

        return false;
    }
}
