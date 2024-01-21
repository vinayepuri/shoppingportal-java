package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.address.Address;

import java.util.List;

public interface AddressRepositoryPort {

    Address findById(Long id);

    List<Address> findByUserId(long userId);

    Address save(Address address);

    void deleteById(Long id);
}
