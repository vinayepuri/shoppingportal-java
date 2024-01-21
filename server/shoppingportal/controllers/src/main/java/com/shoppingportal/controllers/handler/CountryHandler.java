package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.CountryRepositoryJpaAdapter;
import com.ecommerce.domain.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryHandler extends CountryService {
    public CountryHandler(CountryRepositoryJpaAdapter countryRepository) {
        super(countryRepository);
    }
}
