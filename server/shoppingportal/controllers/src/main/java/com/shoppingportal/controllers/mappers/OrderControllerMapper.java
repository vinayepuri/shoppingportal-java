package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.order.OrderDto;
import com.ecommerce.controllers.dto.order.OrderResponse;
import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.order.OrderDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {AddressControllerMapper.class, OrderItemControllerMapper.class})
public interface OrderControllerMapper {

    @Mapping(target = "addressDto", source = "address")
    @Mapping(target = "orderItemDtos", source = "orderItems")
    OrderDto toOrderDto(Order order);

    @Mapping(source = "addressDto", target = "address")
    @Mapping(source = "orderItemDtos", target = "orderItems")
    @Mapping(ignore = true, target = "user")
    Order toToOrder(OrderDto orderDto);

    @Mapping(source ="orders", target ="orderDtos")
    OrderResponse toOrderResponse(OrderDomainResponse orderDomainResponse);


}
