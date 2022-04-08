package com.example.learnspringsecurity.config.exception;

public class CustomResourceExistingException extends RuntimeException{

    public CustomResourceExistingException(String message){
        super(message);
    }

    public CustomResourceExistingException(String message, Throwable cause){
        super(message, cause);
    }
}
