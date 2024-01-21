package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.model.product.ProductsDomainResponse;

import java.util.List;

public interface ProductDriverPort {

    Product getProductById(Long productId);

    ProductsDomainResponse getProducts(int gender, List<Integer> brand, List<Integer> category, int page, int size, String[] sort);

    ProductsDomainResponse getNewProducts(int gender, int page, int size);
}
