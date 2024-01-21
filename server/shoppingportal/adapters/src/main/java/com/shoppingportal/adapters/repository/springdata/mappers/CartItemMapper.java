package com.ecommerce.adapters.repository.springdata.mappers;


import com.ecommerce.domain.model.cart.CartItem;
import com.ecommerce.adapters.repository.springdata.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, CartMapper.class})
public interface CartItemMapper {

    @Mapping(source = "productEntity", target = "product")
    @Mapping(source = "cartEntity", target = "cart")
    CartItem toCartItem(CartItemEntity cartItem);

    @Mapping(ignore = true, target = "productEntity")
    @Mapping(ignore = true, target = "cartEntity")
    CartItemEntity toCartItemEntity(CartItem cartItem);

}
