package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.address.Address;

import java.util.List;

public interface AddressDriverPort {

    Address saveAddress(Address address);

    Address updateAddress(Long id, Address address);

    Address fetchAddress(Long id);

    List<Address> getUserAddress();

    void deleteAddress(Long id);

}
