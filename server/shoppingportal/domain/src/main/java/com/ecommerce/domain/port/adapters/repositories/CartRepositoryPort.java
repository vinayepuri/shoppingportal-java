package com.ecommerce.domain.port.adapters.repositories;


import com.ecommerce.domain.model.cart.Cart;

import java.util.Optional;

public interface CartRepositoryPort {


    Optional<Cart> findCartByUserId(long id);

    void save(Cart cart);
}
