package com.example.backend.exceptions;

public class UserNotExistException extends Exception{
    public UserNotExistException(String message) {
        super(message);
    }
}
