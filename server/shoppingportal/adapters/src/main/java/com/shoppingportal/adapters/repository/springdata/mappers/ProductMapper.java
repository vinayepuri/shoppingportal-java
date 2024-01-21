package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.product.Product;
import com.ecommerce.adapters.repository.springdata.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "genderCategoryEntity", target = "genderCategory")
    @Mapping(source = "apparelCategoryEntity", target = "apparelCategory")
    @Mapping(source = "brandCategoryEntity", target = "brandCategory")
    Product productEntityToProduct(ProductEntity productEntity);

    List<Product> productsToProductsDto(List<ProductEntity> productEntities);
}
