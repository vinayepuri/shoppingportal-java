package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartEntity", orphanRemoval = true)
    List<CartItemEntity> cartItemEntityList = new ArrayList<>();

    public List<CartItemEntity> getCartItems() {
        return Collections.unmodifiableList(cartItemEntityList);
    }

    public void addCartItem(CartItemEntity cartItemEntity) {
        if(cartItemEntity != null) {
            this.cartItemEntityList.add(cartItemEntity);
            cartItemEntity.setCartEntity(this);
        }
    }

    public void clearCartItem(){
        this.cartItemEntityList.clear();
    }

    public void deleteCartItem(CartItemEntity item){
        if(item != null){
            this.cartItemEntityList.remove(item);
        }
    }

}
