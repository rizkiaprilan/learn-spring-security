package com.example.learnspringsecurity.config.exception;

public class CustomResourceNotfoundException extends RuntimeException{

    public CustomResourceNotfoundException(String message){
        super(message);
    }

    public CustomResourceNotfoundException(String message, Throwable cause){
        super(message, cause);
    }
}
