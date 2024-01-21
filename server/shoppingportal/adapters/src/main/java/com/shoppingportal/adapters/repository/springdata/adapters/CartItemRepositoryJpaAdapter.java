package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.domain.port.adapters.repositories.CartItemRepositoryPort;
import com.ecommerce.adapters.repository.springdata.repository.CartItemJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemRepositoryJpaAdapter implements CartItemRepositoryPort {

    private final CartItemJpaRepository cartItemJpaRepository;

    public CartItemRepositoryJpaAdapter(CartItemJpaRepository cartItemJpaRepository) {
        this.cartItemJpaRepository = cartItemJpaRepository;
    }


    @Override
    public void deleteById(Long Id) {
        cartItemJpaRepository.deleteById(Id);
    }
}
