package com.renting.skirent.exceptions;

public class NoRentalsException extends RuntimeException{
    public NoRentalsException(String message) {
        super(message);
    }
}
