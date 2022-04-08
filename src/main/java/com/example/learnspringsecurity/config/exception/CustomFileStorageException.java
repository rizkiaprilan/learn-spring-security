package com.example.learnspringsecurity.config.exception;

public class CustomFileStorageException extends RuntimeException {

    public CustomFileStorageException(String message){
        super(message);
    }

    public CustomFileStorageException(String message, Throwable cause){
        super(message, cause);
    }
}
