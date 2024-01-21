package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.cart.Cart;
import com.ecommerce.adapters.repository.springdata.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    @Mapping(target = "cartItems", ignore = true)
    Cart toCart(CartEntity cart);

}
