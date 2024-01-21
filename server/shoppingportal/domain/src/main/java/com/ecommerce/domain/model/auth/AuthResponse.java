package com.ecommerce.domain.model.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private UserDto user;

    private String token;


    public UserDto getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public AuthResponse(UserDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
