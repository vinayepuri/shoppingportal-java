package com.ecommerce.domain.port.adapters.repositories;


import com.ecommerce.domain.model.order.Order;

import java.util.List;

public interface OrderRepositoryPort{

    List<Order> findOrderByUserId(long id);

    Order save(Order order);
}
