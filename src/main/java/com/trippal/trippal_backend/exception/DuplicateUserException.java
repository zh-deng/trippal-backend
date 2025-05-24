package com.trippal.trippal_backend.exception;

// Custom exception for duplicate users
public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
