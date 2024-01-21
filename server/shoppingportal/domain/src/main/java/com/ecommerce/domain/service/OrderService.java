package com.ecommerce.domain.service;

import com.ecommerce.domain.exception.OrderIsNull;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.AddressRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.domain.util.message.ErrorMessages;
import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.order.OrderDomainResponse;
import com.ecommerce.domain.port.drivers.OrderDriverPort;
import com.ecommerce.domain.port.adapters.repositories.OrderRepositoryPort;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public class OrderService implements OrderDriverPort {

    protected AuthenticationGateway authenticationGateway;
    protected OrderRepositoryPort orderRepository;
    protected ProductRepositoryPort productRepository;
    protected AddressRepositoryPort addressRepository;

    public OrderService(AuthenticationGateway authenticationGateway, OrderRepositoryPort orderRepository, ProductRepositoryPort productRepository, AddressRepositoryPort addressRepository) {
        this.authenticationGateway = authenticationGateway;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        validateOrder(order);
        setOrderTrackingNumber(order);
        setAuthenticatedUser(order);
        return orderRepository.save(order);
    }

    private void setOrderTrackingNumber(Order order) {
        order.setOrderTrackingNumber(generateOrderTrackingNumber());
    }

    @Override
    public OrderDomainResponse fetchUserOrders() {
        User user = authenticationGateway.getAuthenticatedUser();
        List<Order> orders = orderRepository.findOrderByUserId(user.getId());
        return new OrderDomainResponse(orders);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new OrderIsNull(ErrorMessages.ORDER_IS_NULL);
        }
    }

    private String generateOrderTrackingNumber () {
        return UUID.randomUUID().toString();
    }

    private void setAuthenticatedUser(Order order) {
        User user = authenticationGateway.getAuthenticatedUser();
        order.setUser(user);
    }

}
