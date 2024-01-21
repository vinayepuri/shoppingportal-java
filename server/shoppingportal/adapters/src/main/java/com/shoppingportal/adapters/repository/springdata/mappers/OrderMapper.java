package com.ecommerce.adapters.repository.springdata.mappers;


import com.ecommerce.domain.model.order.Order;
import com.ecommerce.adapters.repository.springdata.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "address", source = "addressEntity")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "orderItems", source = "orderItemEntityList")
    @Mapping(ignore = true, target = "user")
    Order toOrder(OrderEntity orderEntity);

    @Mapping(ignore = true, target = "addressEntity")
    @Mapping(ignore = true, target = "userEntity")
    @Mapping(ignore = true, target = "orderItemEntityList")
    OrderEntity toOrderEntity(Order order);

    List<Order> toOrders(List<OrderEntity> orderEntities);

    List<Order> toOrders(Set<OrderEntity> orderEntities);

}
