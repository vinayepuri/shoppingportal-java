package com.ecommerce.domain.model.order;

import com.ecommerce.domain.model.address.Address;
import com.ecommerce.domain.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {

    private Long id;

    private String orderTrackingNumber;

    private Date dateCreated;

    private Address address;
    private List<OrderItem> orderItems;

    private int totalQuantity;

    private BigDecimal totalPrice;

    private User user;

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        if(orderItem != null) {
            orderItems.add(orderItem);
        }
    }

}
