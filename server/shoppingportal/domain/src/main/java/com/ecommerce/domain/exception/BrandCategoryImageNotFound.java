package com.ecommerce.domain.exception;

public class BrandCategoryImageNotFound extends RuntimeException{
    public BrandCategoryImageNotFound(String message, int brandCategoryId) {
        super(message);
    }
}
