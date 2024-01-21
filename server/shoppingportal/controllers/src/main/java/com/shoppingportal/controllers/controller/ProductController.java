package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.dto.product.ProductDto;
import com.ecommerce.controllers.dto.product.ProductsResponse;
import com.ecommerce.controllers.handler.ProductHandler;
import com.ecommerce.controllers.mappers.ProductControllerMapper;
import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.model.product.ProductsDomainResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductHandler productDriverPort;
    private final ProductControllerMapper productMapper;

    public ProductController(ProductHandler productDriverPort, ProductControllerMapper productMapper) {
        this.productDriverPort = productDriverPort;
        this.productMapper = productMapper;
    }


    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        Product product = productDriverPort.getProductById(id);
            return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ProductsResponse> getProducts(@RequestParam(defaultValue = "2") int gender,
                                                              @RequestParam(value = "brand" , defaultValue = "0") List<Integer> brand,
                                                              @RequestParam(value = "category" , defaultValue = "0"  ) List<Integer> category,
                                                              @RequestParam(defaultValue = "0")  int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "id,asc") String[] sort) {

        ProductsDomainResponse productsDomainResponse = productDriverPort.getProducts(gender, brand, category, page, size, sort);
            return new ResponseEntity<>(productMapper.toProductResponse(productsDomainResponse), HttpStatus.OK);

    }


    @GetMapping("/new")
    public ResponseEntity<ProductsResponse> getNewProducts(@RequestParam(defaultValue = "2") int gender,
                                                                 @RequestParam(defaultValue = "0")  int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        ProductsDomainResponse productsDomainResponse = productDriverPort.getNewProducts(gender, page, size);
            return new ResponseEntity<>(productMapper.toProductResponse(productsDomainResponse), HttpStatus.OK);

    }



}
