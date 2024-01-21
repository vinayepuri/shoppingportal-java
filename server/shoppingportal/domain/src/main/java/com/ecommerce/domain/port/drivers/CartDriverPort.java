package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.cart.Cart;



public interface CartDriverPort {

    Cart getCart();

    void saveCart(Cart cart);
}
