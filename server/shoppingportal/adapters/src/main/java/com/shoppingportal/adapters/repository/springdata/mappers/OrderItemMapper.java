package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.order.OrderItem;
import com.ecommerce.adapters.repository.springdata.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderItemMapper {

    @Mapping(ignore = true, target = "productEntity")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "orderEntity")
    OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem);

    @Mapping(source = "productEntity", target = "product")
    OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItem);

    List<OrderItem> orderItemEntitiesToOrderItems(List<OrderItemEntity> orderItemEntities);
    List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> orderItems);
}
