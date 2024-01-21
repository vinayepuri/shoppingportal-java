package com.ecommerce.adapters.repository.springdata.repository;


import com.ecommerce.adapters.repository.springdata.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {


    Optional<CartEntity> findCartByUserEntityId(long id);

}
