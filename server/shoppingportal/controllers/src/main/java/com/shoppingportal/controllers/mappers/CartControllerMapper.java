package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.cart.CartDto;
import com.ecommerce.domain.model.cart.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemControllerMapper.class)
public interface CartControllerMapper {

    @Mapping(target = "cartItems", ignore = true)
    CartDto toCartDto(Cart cart);

    @Mapping(ignore = true, target = "id")
    Cart toCart(CartDto cartDto);
}
