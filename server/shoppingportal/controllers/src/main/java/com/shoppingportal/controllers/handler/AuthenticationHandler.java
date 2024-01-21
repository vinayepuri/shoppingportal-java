package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.CivilityRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.UserRepositoryJpaAdapter;
import com.ecommerce.controllers.security.AuthenticationService;
import com.ecommerce.controllers.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationHandler extends AuthenticationService {

    public AuthenticationHandler(AuthenticationManager authenticationManager, UserRepositoryJpaAdapter userRepository, CivilityRepositoryJpaAdapter civilityRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        super(authenticationManager, userRepository, civilityRepository, encoder, jwtUtils);
    }
}
