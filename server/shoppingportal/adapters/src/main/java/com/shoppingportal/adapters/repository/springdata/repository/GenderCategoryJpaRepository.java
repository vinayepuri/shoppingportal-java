package com.ecommerce.adapters.repository.springdata.repository;

import com.ecommerce.adapters.repository.springdata.entity.GenderCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenderCategoryJpaRepository extends JpaRepository<GenderCategoryEntity, Integer> {


}
