package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.UserRepositoryJpaAdapter;
import com.ecommerce.adapters.security.AuthenticationAdapter;
import com.ecommerce.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserHandler extends UserService {


    public UserHandler(UserRepositoryJpaAdapter userRepository, AuthenticationAdapter authenticationGateway) {
        super(userRepository, authenticationGateway);
    }
}
