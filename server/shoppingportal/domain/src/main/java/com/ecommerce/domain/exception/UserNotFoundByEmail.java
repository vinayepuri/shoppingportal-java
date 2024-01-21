package com.ecommerce.domain.exception;

public class UserNotFoundByEmail extends RuntimeException{

    public UserNotFoundByEmail(String message, String email) {super (message);}
}
