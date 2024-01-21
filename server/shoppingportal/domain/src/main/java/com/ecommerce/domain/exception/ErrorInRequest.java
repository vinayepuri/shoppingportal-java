package com.ecommerce.domain.exception;

public class ErrorInRequest extends RuntimeException{

    public ErrorInRequest(String message) {
        super(message);
    }
}
