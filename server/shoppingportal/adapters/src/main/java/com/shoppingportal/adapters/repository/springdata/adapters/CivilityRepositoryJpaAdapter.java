package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.domain.exception.CivilityNotFound;
import com.ecommerce.domain.model.user.Civility;
import com.ecommerce.domain.port.adapters.repositories.CivilityRepositoryPort;
import com.ecommerce.adapters.repository.springdata.entity.CivilityEntity;
import com.ecommerce.adapters.repository.springdata.mappers.CivilityMapper;
import com.ecommerce.domain.util.message.ErrorMessages;
import com.ecommerce.adapters.repository.springdata.repository.CivilityJpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class CivilityRepositoryJpaAdapter implements CivilityRepositoryPort {

    private final CivilityJpaRepository civilityJpaRepository;
    private final CivilityMapper civilityMapper;

    public CivilityRepositoryJpaAdapter(CivilityJpaRepository civilityJpaRepository, CivilityMapper civilityMapper) {
        this.civilityJpaRepository = civilityJpaRepository;
        this.civilityMapper = civilityMapper;
    }

    @Override
    public Civility findCivilityById(int id) {
        CivilityEntity civilityEntity = civilityJpaRepository.findCivilityEntityById(id)
                .orElseThrow(()-> new CivilityNotFound(ErrorMessages.CIVILITY_NOT_FOUND));
        return civilityMapper.toCivility(civilityEntity);
    }
}
