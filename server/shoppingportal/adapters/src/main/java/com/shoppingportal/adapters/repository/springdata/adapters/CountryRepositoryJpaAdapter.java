package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.domain.port.adapters.repositories.CountryRepositoryPort;
import com.ecommerce.adapters.repository.springdata.mappers.CountryMapper;
import com.ecommerce.adapters.repository.springdata.repository.CountryJpaRepository;
import com.ecommerce.domain.model.country.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CountryRepositoryJpaAdapter implements CountryRepositoryPort {

    private final CountryJpaRepository countryJpaRepository;
    private final CountryMapper countryMapper;

    public CountryRepositoryJpaAdapter(CountryJpaRepository countryJpaRepository, CountryMapper countryMapper) {
        this.countryJpaRepository = countryJpaRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<Country> findAll() {
        return countryJpaRepository.findAll().stream()
                .map(countryMapper::toCountry)
                .collect(Collectors.toList());
    }
}
