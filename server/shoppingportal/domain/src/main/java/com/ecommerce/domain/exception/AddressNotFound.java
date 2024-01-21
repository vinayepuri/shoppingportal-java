package com.ecommerce.domain.exception;

public class AddressNotFound extends RuntimeException{
    public AddressNotFound(String message) {
        super(message);
    }
}
