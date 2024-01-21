package com.ecommerce.adapters.repository.springdata.repository;

import com.ecommerce.adapters.repository.springdata.entity.CivilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CivilityJpaRepository extends JpaRepository<CivilityEntity, Integer> {

    Optional<CivilityEntity> findCivilityEntityById(int Id);
}
