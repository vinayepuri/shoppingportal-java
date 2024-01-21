package com.ecommerce.domain.model.product;

import com.ecommerce.domain.model.category.ApparelCategory;
import com.ecommerce.domain.model.category.BrandCategory;
import com.ecommerce.domain.model.category.GenderCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private Long id;
    private String productName;
    private BigDecimal unitPrice;
    private String imageUrl;
    private Boolean active;
    private int unitsInStocks;

    private GenderCategory genderCategory;

    private BrandCategory brandCategory;

    private ApparelCategory apparelCategory;

    public Product(Long id, String productName, BigDecimal unitPrice, String imageUrl, Boolean active, int unitsInStocks) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.active = active;
        this.unitsInStocks = unitsInStocks;
    }

    public Product() {

    }
}
