package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.CartRepositoryJpaAdapter;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartHandler extends CartService {

    public CartHandler(AuthenticationGateway authenticationGateway, CartRepositoryJpaAdapter cartRepository) {
        super(authenticationGateway, cartRepository);
    }
}
