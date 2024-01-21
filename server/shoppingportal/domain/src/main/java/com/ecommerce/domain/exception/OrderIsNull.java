package com.ecommerce.domain.exception;

public class OrderIsNull extends RuntimeException{
    public OrderIsNull(String message) {
        super(message);
    }
}
