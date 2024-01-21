package com.ecommerce.domain.model.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDomainResponse {

    private List<Order> orders;


    public OrderDomainResponse(List<Order> orderDtos) {
        this.orders = orderDtos;
    }
}
