package com.example.Ex7_Restful.API_Spring.Exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}