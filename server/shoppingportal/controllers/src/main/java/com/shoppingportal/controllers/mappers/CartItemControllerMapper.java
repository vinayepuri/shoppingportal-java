package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.cart.CartItemDto;
import com.ecommerce.domain.model.cart.Cart;
import com.ecommerce.domain.model.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductControllerMapper.class)
public interface CartItemControllerMapper {

    @Mapping(source = "product", target = "productDto")
    CartItemDto cartItemToCartItemDto(CartItem cartItem);

    @Mapping(source = "productDto", target = "product")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "cart")
    CartItem cartItemDtoToCartItem(CartItemDto cartItemDto);

    List<CartItem> toCartItems(List<CartItemDto> cartItemDtos);
    List<CartItemDto> toCartItemDtos(List<Cart> cartItems);

}
