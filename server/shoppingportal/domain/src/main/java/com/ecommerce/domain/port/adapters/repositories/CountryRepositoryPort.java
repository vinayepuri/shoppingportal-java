package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.country.Country;

import java.util.List;

public interface CountryRepositoryPort {
    List<Country> findAll();
}
