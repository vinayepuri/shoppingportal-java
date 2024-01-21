package com.ecommerce.adapters.repository.springdata.repository;

import com.ecommerce.adapters.repository.springdata.entity.BrandCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandCategoryJpaRepository extends JpaRepository<BrandCategoryEntity, Integer> {

    @Query("SELECT DISTINCT b FROM BrandCategoryEntity b" +
            " JOIN b.productEntitySet p" +
            " JOIN b.genderCategoryEntitySet g" +
            " WHERE g.id = :genderId" +
            " AND p.genderCategoryEntity.id = :genderId" +
            " GROUP BY b" +
            " HAVING COUNT(p) > 0")
    List<BrandCategoryEntity> findByGenderCategoryIdAndProductsIsNotNull(@Param("genderId") int genderId);

    @Query("SELECT DISTINCT b FROM BrandCategoryEntity b" +
            " JOIN b.productEntitySet p" +
            " JOIN b.apparelCategoryEntitySet a" +
            " JOIN b.genderCategoryEntitySet g" +
            " WHERE a.id = :apparelCategoryId" +
            " AND g.id = :genderId" +
            " AND p.genderCategoryEntity.id = :genderId" +
            " AND p.apparelCategoryEntity.id = :apparelCategoryId" +
            " GROUP BY b" +
            " HAVING COUNT(p) > 0")
    List<BrandCategoryEntity> findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(@Param("genderId") int genderId, @Param("apparelCategoryId") int apparelCategoryId);

    @Query("SELECT a FROM BrandCategoryEntity a WHERE a.id IN :brandCategoryIds")
    List<BrandCategoryEntity> findByIdIn(@Param("brandCategoryIds") List<Integer> brandCategoryIds);


}
