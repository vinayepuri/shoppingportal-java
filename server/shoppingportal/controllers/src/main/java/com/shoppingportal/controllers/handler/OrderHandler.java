package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.AddressRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.OrderRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.ProductRepositoryJpaAdapter;
import com.ecommerce.adapters.security.AuthenticationAdapter;
import com.ecommerce.domain.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderHandler extends OrderService {

    public OrderHandler(AuthenticationAdapter authenticationGateway, OrderRepositoryJpaAdapter orderRepository, ProductRepositoryJpaAdapter productRepository, AddressRepositoryJpaAdapter addressRepository) {
        super(authenticationGateway, orderRepository, productRepository, addressRepository);
    }
}
