package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.domain.model.category.GenderCategory;
import com.ecommerce.adapters.repository.springdata.mappers.GenderCategoryMapper;
import com.ecommerce.domain.exception.GenderNotFound;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.adapters.repository.springdata.entity.GenderCategoryEntity;
import com.ecommerce.adapters.repository.springdata.repository.GenderCategoryJpaRepository;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GenderCategoryRepositoryJpaAdapter implements GenderCategoryRepositoryPort {

    private final GenderCategoryJpaRepository genderCategoryJpaRepository;
    private final GenderCategoryMapper genderCategoryMapper;

    public GenderCategoryRepositoryJpaAdapter(GenderCategoryJpaRepository genderCategoryJpaRepository, GenderCategoryMapper genderCategoryMapper) {
        this.genderCategoryJpaRepository = genderCategoryJpaRepository;
        this.genderCategoryMapper = genderCategoryMapper;
    }

    @Override
    public List<GenderCategory> findAll() {
        return genderCategoryJpaRepository.findAll().stream()
                .map(genderCategoryMapper::toGenderCategory)
                .collect(Collectors.toList());
    }

    @Override
    public GenderCategory findById(int gender) {
        GenderCategoryEntity genderCategoryEntity = genderCategoryJpaRepository.findById(gender)
                .orElseThrow(()-> new GenderNotFound(ErrorMessages.GENDER_NOT_FOUND));
        return genderCategoryMapper.toGenderCategory(genderCategoryEntity);
    }
}
