package com.ecommerce.domain.service;


import com.ecommerce.domain.model.country.Country;
import com.ecommerce.domain.port.drivers.CountryDriverPort;
import com.ecommerce.domain.port.adapters.repositories.CountryRepositoryPort;

import java.util.List;

public class CountryService implements CountryDriverPort {

    protected CountryRepositoryPort countryRepository;

    public CountryService(CountryRepositoryPort countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> fetchCountries() {

        return countryRepository.findAll();

    }
}
