package com.ecommerce.domain.model.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private int civility;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public SignupRequest(int civility, String firstName, String lastName, String email, String password) {
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
