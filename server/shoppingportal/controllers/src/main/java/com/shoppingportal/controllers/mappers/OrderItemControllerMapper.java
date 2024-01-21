package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.order.OrderItemDto;
import com.ecommerce.domain.model.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductControllerMapper.class)
public interface OrderItemControllerMapper {

    @Mapping(source = "productDto", target = "product")
    OrderItem toOrderItem(OrderItemDto orderItemDto);

    @Mapping(source = "product", target = "productDto")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    List<OrderItemDto> ordersItemsToOrderItemsDto(List<OrderItem> orderItems);

}
