package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.AddressRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.CivilityRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.CountryRepositoryJpaAdapter;
import com.ecommerce.adapters.security.AuthenticationAdapter;
import com.ecommerce.domain.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressHandler extends AddressService {

    public AddressHandler(AuthenticationAdapter authenticationGateway, AddressRepositoryJpaAdapter addressRepository, CountryRepositoryJpaAdapter countryRepository, CivilityRepositoryJpaAdapter civilityRepository) {
        super(authenticationGateway, addressRepository, countryRepository, civilityRepository);
    }


}
