package com.ecommerce.domain.model.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Cart {
    Long id;

    List<CartItem> cartItems = new ArrayList<>();

    private int totalQuantity;

    private BigDecimal totalPrice;


    public void addCartItem(CartItem cartItem) {
        if(cartItem != null) {
            this.cartItems.add(cartItem);
            cartItem.setCart(this);
        }
    }

    public void clearCartItem(){
        this.cartItems.clear();
    }

    public void deleteCartItem(CartItem item){
        if(item != null){
            this.cartItems.remove(item);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return totalQuantity == cart.totalQuantity && Objects.equals(id, cart.id) && Objects.equals(cartItems, cart.cartItems) && Objects.equals(totalPrice, cart.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItems, totalQuantity, totalPrice);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

