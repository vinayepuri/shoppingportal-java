package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.dto.order.OrderDto;
import com.ecommerce.controllers.dto.order.OrderResponse;
import com.ecommerce.controllers.handler.OrderHandler;
import com.ecommerce.controllers.mappers.OrderControllerMapper;
import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.order.OrderDomainResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderHandler orderDriverPort;
    private final OrderControllerMapper orderControllerMapper;

    public OrderController(OrderHandler orderDriverPort, OrderControllerMapper orderControllerMapper) {
        this.orderDriverPort = orderDriverPort;
        this.orderControllerMapper = orderControllerMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderDto orderDto) {
        Order order = orderControllerMapper.toToOrder(orderDto);
        Order savedOrder = orderDriverPort.saveOrder(order);
            return new ResponseEntity<>(orderControllerMapper.toOrderDto(savedOrder), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<OrderResponse> getOrders() {
        OrderDomainResponse orderDomainResponse = orderDriverPort.fetchUserOrders();
        OrderResponse dto = orderControllerMapper.toOrderResponse(orderDomainResponse);
            return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
