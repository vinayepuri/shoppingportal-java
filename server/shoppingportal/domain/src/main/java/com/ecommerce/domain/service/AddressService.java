package com.ecommerce.domain.service;

import com.ecommerce.domain.exception.AddressAlreadyExists;
import com.ecommerce.domain.exception.CheckingUserAddressFailed;
import com.ecommerce.domain.model.address.Address;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.*;
import com.ecommerce.domain.port.drivers.AddressDriverPort;
import com.ecommerce.domain.util.message.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

public class AddressService implements AddressDriverPort {

    protected AuthenticationGateway authenticationGateway;
    protected AddressRepositoryPort addressRepository;
    protected CountryRepositoryPort countryRepository;
    protected CivilityRepositoryPort civilityRepository;

    public AddressService(AuthenticationGateway authenticationGateway, AddressRepositoryPort addressRepository, CountryRepositoryPort countryRepository, CivilityRepositoryPort civilityRepository) {
        this.authenticationGateway = authenticationGateway;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.civilityRepository = civilityRepository;
    }


    @Override
    public Address fetchAddress(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address saveAddress(Address address) {
        checkAddressExistence(address.getId());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        Address addressFromDB = fetchAddress(id);
        checkUserAddress(addressFromDB);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getUserAddress() {
        User user = authenticationGateway.getAuthenticatedUser();
        List<Address> addresses = addressRepository.findByUserId(user.getId());

        if (addresses.isEmpty()) {
            return new ArrayList<Address>();
        }
        return new ArrayList<>(addresses);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = fetchAddress(id);
        this.checkUserAddress(address);
        addressRepository.deleteById(id);
    }

    private void checkAddressExistence(Long id) {
        if(addressRepository.findById(id) != null) {
            throw new AddressAlreadyExists(ErrorMessages.ADDRESS_ALREADY_EXISTS);
        };
    }

    private void checkUserAddress(Address address){
        User user = authenticationGateway.getAuthenticatedUser();
        if(user != address.getUser()){
            throw new CheckingUserAddressFailed(ErrorMessages.CHECKING_USER_ADDRESS_FAILED);
        }
    }

}
