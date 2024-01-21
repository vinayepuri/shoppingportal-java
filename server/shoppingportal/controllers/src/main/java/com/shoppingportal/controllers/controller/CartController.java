package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.dto.cart.CartDto;
import com.ecommerce.controllers.handler.CartHandler;
import com.ecommerce.controllers.mappers.CartControllerMapper;
import com.ecommerce.domain.model.cart.Cart;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartHandler cartDriverPort;
    private final CartControllerMapper cartControllerMapper;

    public CartController(CartHandler cartDriverPort, CartControllerMapper cartControllerMapper) {
        this.cartDriverPort = cartDriverPort;
        this.cartControllerMapper = cartControllerMapper;
    }

    @GetMapping
    public ResponseEntity<CartDto> getCartByUser() {
        Cart cart = cartDriverPort.getCart();
            return new ResponseEntity<>(cartControllerMapper.toCartDto(cart), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveUserCart(@Valid @RequestBody CartDto cartDTO) {
        Cart cart = cartControllerMapper.toCart(cartDTO);
        cartDriverPort.saveCart(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
