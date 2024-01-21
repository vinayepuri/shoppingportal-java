package com.ecommerce.domain.exception;


public class AddressAlreadyExists extends RuntimeException{

    public AddressAlreadyExists(String message) {
        super(message);
    }
}
