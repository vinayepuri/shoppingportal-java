package com.ecommerce.adapters.repository.springdata.repository;

import com.ecommerce.adapters.repository.springdata.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findByGenderCategoryEntityId(int gender, Pageable pageable);

    Page<ProductEntity> findByGenderCategoryEntityIdAndBrandCategoryEntityIdIn(int gender, List<Integer> brand, Pageable pageable);

    Page<ProductEntity> findByGenderCategoryEntityIdAndApparelCategoryEntityIdIn(int gender, List<Integer> category, Pageable pageable);

    Page<ProductEntity> findByGenderCategoryEntityIdAndApparelCategoryEntityIdInAndBrandCategoryEntityIdIn(int gender, List<Integer> category, List<Integer> brand, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p" +
            " WHERE p.newProduct = true AND p.genderCategoryEntity.id = :genderId" +
    " ORDER BY p.id")
    Page<ProductEntity> findNewProductByGenderCategoryId(@Param("genderId") int genderId, Pageable pageable);

}
