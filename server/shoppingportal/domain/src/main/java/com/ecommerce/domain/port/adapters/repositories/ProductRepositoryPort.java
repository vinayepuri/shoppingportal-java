package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryPort {

    Page<Product> findByGenderCategoryId(int gender, Pageable pageable);

    Page<Product> findByGenderCategoryIdAndBrandCategoryIdIn(int gender, List<Integer> brand, Pageable pageable);

    Page<Product> findByGenderCategoryIdAndApparelCategoryIdIn(int gender, List<Integer> category, Pageable pageable);

    Page<Product> findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(int gender, List<Integer> brand, List<Integer> category,  Pageable pageable);


    Page<Product> findNewProductByGenderCategoryId(int genderId, Pageable pageable);

    Product findById(Long productId);
}
