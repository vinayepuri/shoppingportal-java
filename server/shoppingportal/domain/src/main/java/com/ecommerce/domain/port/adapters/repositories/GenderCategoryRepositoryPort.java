package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.category.GenderCategory;

import java.util.List;

public interface GenderCategoryRepositoryPort {

    List<GenderCategory> findAll();

    GenderCategory findById(int gender);
}
