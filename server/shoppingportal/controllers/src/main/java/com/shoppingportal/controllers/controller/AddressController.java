package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.mappers.AddressControllerMapper;
import com.ecommerce.controllers.dto.address.AddressDto;
import com.ecommerce.controllers.dto.address.AddressResponse;
import com.ecommerce.controllers.handler.AddressHandler;
import com.ecommerce.domain.model.address.Address;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressHandler addressDriverPort;
    private final AddressControllerMapper addressConverter;

    public AddressController(AddressHandler addressDriverPort, AddressControllerMapper addressConverter) {
        this.addressDriverPort = addressDriverPort;
        this.addressConverter = addressConverter;
    }

    @GetMapping
    public ResponseEntity<AddressResponse> getAddresses() {
        List<Address> addressList = addressDriverPort.getUserAddress();
        List<AddressDto> addressDtoList = convertAddressListToAddressDtoList(addressList);
        return ResponseEntity.ok(new AddressResponse(addressDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable("id") Long id) {
        Address address = addressDriverPort.fetchAddress(id);
        return ResponseEntity.ok(addressConverter.toAddressDto(address));
    }

    @PostMapping
    public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto) {
        Address address = addressConverter.toAddress(addressDto);
        Address savedAddress = addressDriverPort.saveAddress(address);
        return ResponseEntity.ok(addressConverter.toAddressDto(savedAddress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto addressDto,
                                                    @PathVariable("id") Long id) {
        Address address = addressConverter.toAddress(addressDto);
        Address updatedAddress = addressDriverPort.updateAddress(id, address);
            return ResponseEntity.ok(addressConverter.toAddressDto(updatedAddress));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        addressDriverPort.deleteAddress(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<AddressDto> convertAddressListToAddressDtoList(List<Address> addressList) {
        List<AddressDto> addressDtoList = addressList.stream()
                .map(address -> addressConverter.toAddressDto(address))
                .collect(Collectors.toList());
        return addressDtoList;
    }

}
