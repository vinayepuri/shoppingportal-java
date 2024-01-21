package com.ecommerce.adapters.security.port;

import com.ecommerce.domain.model.auth.AuthResponse;
import com.ecommerce.domain.model.auth.SignupRequest;
import com.ecommerce.domain.model.auth.AuthRequest;

public interface AuthenticationDriverPort {
    AuthResponse signIn(AuthRequest authRequest);

    void signUp(SignupRequest signupRequest);
}
