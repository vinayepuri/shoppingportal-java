package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.product.ProductDto;
import com.ecommerce.controllers.dto.product.ProductsResponse;
import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.model.product.ProductsDomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductControllerMapper {

    List<ProductDto> productsToProductsDto(List<Product> products);
    @Mapping(source = "genderCategory.name", target = "gender_category")
    @Mapping(source = "apparelCategory.name", target = "product_category")
    @Mapping(source = "brandCategory.name", target = "brand_category")
    ProductDto toProductDto(Product product);

    @Mapping(source="products", target="productsDTO")
    ProductsResponse toProductResponse(ProductsDomainResponse productsDomainResponse);
}
