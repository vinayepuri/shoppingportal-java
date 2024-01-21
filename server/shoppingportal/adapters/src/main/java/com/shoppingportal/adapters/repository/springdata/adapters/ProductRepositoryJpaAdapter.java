package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.domain.exception.ProductNotFound;
import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.adapters.repository.springdata.entity.ProductEntity;
import com.ecommerce.adapters.repository.springdata.mappers.ProductMapper;
import com.ecommerce.domain.util.message.ErrorMessages;
import com.ecommerce.adapters.repository.springdata.repository.ProductJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryJpaAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryJpaAdapter(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper =productMapper;
    }

    @Override
    public Page<Product> findByGenderCategoryId(int gender, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryEntityId(gender, pageable);
        return pageProductEntity.map(productMapper::productEntityToProduct);
    }

    @Override
    public Page<Product> findByGenderCategoryIdAndBrandCategoryIdIn(int gender, List<Integer> brand, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryEntityIdAndBrandCategoryEntityIdIn(gender, brand, pageable);
        return pageProductEntity.map(productMapper::productEntityToProduct);
    }

    @Override
    public Page<Product> findByGenderCategoryIdAndApparelCategoryIdIn(int gender, List<Integer> category, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryEntityIdAndApparelCategoryEntityIdIn(gender, category, pageable);
        return pageProductEntity.map(productMapper::productEntityToProduct);
    }

    @Override
    public Page<Product> findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(int gender, List<Integer> brand, List<Integer> category, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryEntityIdAndApparelCategoryEntityIdInAndBrandCategoryEntityIdIn(gender, category, brand, pageable);
        return pageProductEntity.map(productMapper::productEntityToProduct);
    }

    @Override
    public Page<Product> findNewProductByGenderCategoryId(int genderId, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findNewProductByGenderCategoryId(genderId, pageable);
        return pageProductEntity.map(productMapper::productEntityToProduct);
    }

    @Override
    public Product findById(Long productId) {
        ProductEntity productEntity = productJpaRepository.findById(productId).orElseThrow(() -> new ProductNotFound(ErrorMessages.PRODUCT_NOT_FOUND));
        return productMapper.productEntityToProduct(productEntity);
    }
}
