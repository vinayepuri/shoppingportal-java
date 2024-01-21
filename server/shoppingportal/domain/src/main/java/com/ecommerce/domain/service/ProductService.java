package com.ecommerce.domain.service;

import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.model.product.ProductsDomainResponse;
import com.ecommerce.domain.port.adapters.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.domain.port.drivers.ProductDriverPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public class ProductService implements ProductDriverPort {

    protected ProductRepositoryPort productRepository;
    protected GenderCategoryRepositoryPort genderCategoryRepositoryPort;
    protected ApparelCategoryRepositoryPort apparelCategoryRepositoryPort;
    protected BrandCategoryRepositoryPort brandCategoryRepositoryPort;

    public ProductService(ProductRepositoryPort productRepository, GenderCategoryRepositoryPort genderCategoryRepositoryPort, ApparelCategoryRepositoryPort apparelCategoryRepositoryPort, BrandCategoryRepositoryPort brandCategoryRepositoryPort) {
        this.productRepository = productRepository;
        this.genderCategoryRepositoryPort = genderCategoryRepositoryPort;
        this.apparelCategoryRepositoryPort = apparelCategoryRepositoryPort;
        this.brandCategoryRepositoryPort = brandCategoryRepositoryPort;
    }


    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ProductsDomainResponse getProducts(int gender, List<Integer> brand, List<Integer> category, int page, int size, String[] sort) {
        Pageable pagingSort = constructPageableWithSorting(page, size, sort);
        Page<Product> pageProduct = fetchProducts(gender, category, brand,  pagingSort);

        return constructProductsResponse(pageProduct);
    }

    @Override
    public ProductsDomainResponse getNewProducts(int gender, int page, int size) {
        Pageable pagingSort = PageRequest.of(page, size);
        Page<Product> pageProduct = productRepository.findNewProductByGenderCategoryId(gender, pagingSort);

        return constructProductsResponse(pageProduct);
    }

    private Pageable constructPageableWithSorting(int page, int size, String[] sort) {
        Sort.Direction direction = getSortDirection(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        return PageRequest.of(page, size, Sort.by(order));
    }

    public Sort.Direction getSortDirection (String direction){
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Page<Product> fetchProducts(int gender, List<Integer> categoryIds, List<Integer> brandIds, Pageable pagingSort) {
        checkGenderExists(gender);

        if (apparelCategoriesNotSet(categoryIds) && brandCategoriesNotSet(brandIds))
            return fetchAllProductsByGender(gender, pagingSort);

        if (apparelCategoriesNotSet(categoryIds)) {
            checkBrandCategoryListExists(brandIds);
            return fetchProductsByGenderAndBrands(gender, brandIds, pagingSort);
        }

        if (brandCategoriesNotSet(brandIds)) {
            checkApparelCategoryListExists(categoryIds);
            return fetchProductsByGenderAndApparelCategories(gender, categoryIds, pagingSort);
        }

        checkBrandCategoryListExists(brandIds);
        checkApparelCategoryListExists(categoryIds);
        return fetchProductsByGenderAndApparelCategoriesAndBrands(gender, categoryIds, brandIds, pagingSort);
    }

    private Page<Product> fetchProductsByGenderAndApparelCategoriesAndBrands(int gender, List<Integer> categoryIds, List<Integer> brandIds, Pageable pagingSort) {
        return productRepository.findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(gender, brandIds, categoryIds, pagingSort);
    }

    private Page<Product> fetchProductsByGenderAndApparelCategories(int gender, List<Integer> categoryIds, Pageable pagingSort) {
        return productRepository.findByGenderCategoryIdAndApparelCategoryIdIn(gender, categoryIds, pagingSort);
    }

    private Page<Product> fetchProductsByGenderAndBrands(int gender, List<Integer> brandIds, Pageable pagingSort) {
        return productRepository.findByGenderCategoryIdAndBrandCategoryIdIn(gender, brandIds, pagingSort);
    }


    private Page<Product> fetchAllProductsByGender(int gender, Pageable pagingSort) {
        return productRepository.findByGenderCategoryId(gender, pagingSort);
    }

    private boolean brandCategoriesNotSet(List<Integer> brandIds) {
        return brandIds.contains(0);
    }
    private boolean apparelCategoriesNotSet(List<Integer> categoryIds) {
        return categoryIds.contains(0);
    }

    private void checkBrandCategoryListExists(List<Integer> brandIds) {
        brandCategoryRepositoryPort.findByIdIn(brandIds);
    }

    private void checkApparelCategoryListExists(List<Integer> categoryIds) {
        apparelCategoryRepositoryPort.findByIdIn(categoryIds);
    }

    private void checkGenderExists(int genderId) {
        genderCategoryRepositoryPort.findById(genderId);
    }

    private ProductsDomainResponse constructProductsResponse(Page<Product> pageProduct) {
        ProductsDomainResponse productsDomainResponse = new ProductsDomainResponse();

        if (pageProduct == null || pageProduct.isEmpty()) {
            productsDomainResponse.setProducts(Collections.emptyList());
            productsDomainResponse.setCurrentPage(0);
            productsDomainResponse.setSize(1);
            productsDomainResponse.setTotalItems(0L);
            productsDomainResponse.setTotalPages(1);
        } else {
            productsDomainResponse.setProducts(pageProduct.getContent());
            productsDomainResponse.setCurrentPage(pageProduct.getNumber());
            productsDomainResponse.setSize(pageProduct.getSize());
            productsDomainResponse.setTotalItems(pageProduct.getTotalElements());
            productsDomainResponse.setTotalPages(pageProduct.getTotalPages());
        }
        return productsDomainResponse;
    }

}
