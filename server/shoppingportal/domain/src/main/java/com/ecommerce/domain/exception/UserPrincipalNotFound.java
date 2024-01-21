package com.ecommerce.domain.exception;

public class UserPrincipalNotFound extends RuntimeException{
    public UserPrincipalNotFound(String message) {
        super(message);
    }
}
