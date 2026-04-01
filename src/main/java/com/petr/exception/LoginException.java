package com.petr.exception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{
    private final int statusCode;

    public LoginException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
