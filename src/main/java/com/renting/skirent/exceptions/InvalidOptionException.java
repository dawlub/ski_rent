package com.renting.skirent.exceptions;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(String message) {
        super("Option not exist");

    }
}

