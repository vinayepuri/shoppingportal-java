package com.ecommerce.domain.exception;

public class CheckingUserAddressFailed extends RuntimeException{
    public CheckingUserAddressFailed(String message) {
        super(message);
    }
}
