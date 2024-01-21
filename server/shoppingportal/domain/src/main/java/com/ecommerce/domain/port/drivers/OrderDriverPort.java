package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.order.OrderDomainResponse;

public interface OrderDriverPort {

    Order saveOrder(Order order);

    OrderDomainResponse fetchUserOrders();
}
