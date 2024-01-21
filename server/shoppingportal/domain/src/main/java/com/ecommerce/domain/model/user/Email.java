package com.ecommerce.domain.model.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    private String email;


    public Email(String email) {
        this.email = email;
    }
}
