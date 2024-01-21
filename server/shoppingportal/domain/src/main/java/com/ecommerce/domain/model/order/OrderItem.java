package com.ecommerce.domain.model.order;

import com.ecommerce.domain.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItem {

    private Product product;

    private int quantity;

    private BigDecimal amount;


}
