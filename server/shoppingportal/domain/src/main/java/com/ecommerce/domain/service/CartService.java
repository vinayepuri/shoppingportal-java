package com.ecommerce.domain.service;


import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.model.cart.Cart;
import com.ecommerce.domain.model.cart.CartItem;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.CartRepositoryPort;
import com.ecommerce.domain.port.drivers.CartDriverPort;

import java.util.Map;
import java.util.stream.Collectors;

public class CartService implements CartDriverPort {

    protected AuthenticationGateway authenticationGateway;
    protected CartRepositoryPort cartRepository;

    public CartService(AuthenticationGateway authenticationGateway, CartRepositoryPort cartRepository) {
        this.authenticationGateway = authenticationGateway;
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCart() {
        User user = authenticationGateway.getAuthenticatedUser();
        return cartRepository.findCartByUserId(user.getId())
                .orElseGet(Cart::new);
    }

    @Override
    public void saveCart(Cart cart) {
        Cart cartToBeUpdated = getCart();
        updateCart(cartToBeUpdated, cart);
        cartRepository.save(cartToBeUpdated);
    }

    private void updateCart(Cart cartFromDB, Cart cart) {
        Map<Long, CartItem> cartItemMap = getCartItemsMap(cart);

        clearItemsThatAreNoLongerPresentInTheNewCart(cartFromDB, cartItemMap);

        updateCartItems(cartFromDB, cart, cartItemMap);

        cartFromDB.setTotalPrice(cart.getTotalPrice());
        cartFromDB.setTotalQuantity(cart.getTotalQuantity());
    }

    private Map<Long, CartItem> getCartItemsMap(Cart cart) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct() != null)
                .collect(Collectors.toMap(item -> item.getProduct().getId(), item -> item));
    }
    private void clearItemsThatAreNoLongerPresentInTheNewCart(Cart cartFromDB, Map<Long, CartItem> cartItemMap) {
        cartFromDB.getCartItems().removeIf(item -> !cartItemMap.containsKey(item.getProduct().getId()));
    }

    private void updateCartItems(Cart cartFromDB, Cart cart, Map<Long, CartItem> cartItemMap) {
        cart.getCartItems().forEach(cartItem -> {
            CartItem _cartItem = cartItemMap.getOrDefault(cartItem.getProduct().getId(), null);
            if (_cartItem == null) {
                cartFromDB.addCartItem(cartItem);
            } else {
                updateExistingItem(cartItem, _cartItem);
            }
        });
    }
    private void updateExistingItem(CartItem cartItem, CartItem _cartItem) {
        _cartItem.setQuantity(cartItem.getQuantity());
        _cartItem.setAmount(cartItem.getAmount());
    }

}
