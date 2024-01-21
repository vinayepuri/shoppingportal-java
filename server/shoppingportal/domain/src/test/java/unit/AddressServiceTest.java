package unit;

import com.ecommerce.domain.exception.AddressAlreadyExists;
import com.ecommerce.domain.exception.CheckingUserAddressFailed;
import com.ecommerce.domain.model.address.Address;
import com.ecommerce.domain.model.country.Country;
import com.ecommerce.domain.model.user.Civility;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.AddressRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.CivilityRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.CountryRepositoryPort;
import com.ecommerce.domain.port.drivers.AddressDriverPort;
import com.ecommerce.domain.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    private AddressDriverPort addressService;
    @Mock
    private AuthenticationGateway authenticationGateway;
    @Mock
    private AddressRepositoryPort addressRepository;
    @Mock
    private CountryRepositoryPort countryRepository;
    @Mock
    private CivilityRepositoryPort civilityRepository;

    @BeforeEach
    void setup() {
        addressService = new AddressService(authenticationGateway, addressRepository, countryRepository, civilityRepository);
    }

    @Test
    @DisplayName("Test saveAddress - Failure - Address already exists")
    void givenExistingAddress_whenSavingAddress_thenThrowException() {
        Long addressId = 1L;
        Address existingAddress = new Address();
        existingAddress.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(existingAddress);

        assertThrows(AddressAlreadyExists.class, () -> addressService.saveAddress(existingAddress));
    }

    @Test
    @DisplayName("Test saveAddress - Success")
    void givenNewAddressWithDetails_whenSavingAddress_thenAddressIsSaved() {
        Long AddressId = 1L;
        Country mockCountry = new Country(1, "France", "FR");
        Civility mockCivility = new Civility(1, "M");
        Address newAddress = new Address(AddressId, mockCivility, "John", "Doe", "123 Main Street", mockCountry);

        when(addressRepository.findById(AddressId)).thenReturn(null);
        when(addressRepository.save(newAddress)).thenReturn(newAddress);

        Address result = addressService.saveAddress(newAddress);

        verify(addressRepository).save(newAddress);
        assertEquals(newAddress, result);
    }


    @Test
    @DisplayName("Test updatingAddress - Success")
    void givenExistingAddressAndUserMatch_whenUpdatingAddress_thenAddressIsUpdated() {
        Long addressId = 1L;
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        Address existingAddress = new Address();
        existingAddress.setId(addressId);
        existingAddress.setUser(authenticatedUser);

        Country mockCountry = new Country(1, "France", "FR");
        Civility mockCivility = new Civility(1, "M");
        Address addressToUpdate = new Address(addressId, mockCivility, "John", "Doe", "123 Main Street", mockCountry);

        when(addressRepository.findById(addressId)).thenReturn(existingAddress);
        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(addressRepository.save(addressToUpdate)).thenReturn(addressToUpdate);

        Address result = addressService.updateAddress(addressId, addressToUpdate);

        verify(addressRepository).save(addressToUpdate);
        assertEquals(addressToUpdate, result);
    }

    @Test
    @DisplayName("Test updateAddress - Failure - Check User Address failed")
    void givenExistingAddressAndUserMisMatch_whenUpdatingAddress_thenThrowException() {
        Long addressId = 1L;
        User notAuthenticatedUser = new User();
        notAuthenticatedUser.setId(99L);
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);

        Address addressFromDb = new Address();
        addressFromDb.setId(addressId);
        addressFromDb.setUser(notAuthenticatedUser);

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(addressRepository.findById(addressId)).thenReturn(addressFromDb);

        assertThrows(CheckingUserAddressFailed.class, () -> addressService.updateAddress(addressId, new Address()));
    }

    @Test
    void givenAuthenticatedUserWithAddresses_whenFetchingUserAddresses_thenAddressesAreReturned() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        List<Address> addresses = Arrays.asList(new Address(), new Address());

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(addressRepository.findByUserId(authenticatedUser.getId())).thenReturn(addresses);

        List<Address> result = addressService.getUserAddress();

        assertEquals(2, result.size());
    }

    @Test
    void givenAuthenticatedUserWithNoAddress_whenFetchingUserAddresses_thenEmptyListIsReturned() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(addressRepository.findByUserId(authenticatedUser.getId())).thenReturn(Collections.emptyList());

        List<Address> result = addressService.getUserAddress();

        assertTrue(result.isEmpty());
    }

    @Test
    void givenExistingAddressId_whenDeletingAddressById_thenAddressIsDeleted() {
        Long addressId = 1L;
        Address addressToDelete = new Address();
        addressToDelete.setId(addressId);
        User authenticatedUser = new User();
        addressToDelete.setUser(authenticatedUser);


        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(addressRepository.findById(addressId)).thenReturn(addressToDelete);

        addressService.deleteAddress(addressId);

        verify(addressRepository).deleteById(addressId);
    }

    @Test
    void givenExistingAddressAndUserMismatch_whenDeletingAddress_thenThrowException() {
        Long addressId = 1L;
        Address addressFromDb = new Address();
        User unauthenticatedUser = new User();
        addressFromDb.setUser(unauthenticatedUser);
        addressFromDb.setId(addressId);
        User authenticatedUser = new User();

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(addressRepository.findById(addressId)).thenReturn(addressFromDb);

        assertThrows(CheckingUserAddressFailed.class, () -> addressService.deleteAddress(addressId));
    }

}
