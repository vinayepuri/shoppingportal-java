package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.country.Country;

import java.util.List;

public interface CountryDriverPort {

    List<Country> fetchCountries();
}
