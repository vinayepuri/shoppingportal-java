package com.ecommerce.domain.exception;

public class CountryNotFound extends RuntimeException{
    public CountryNotFound(String message) {
        super(message);
    }
}
