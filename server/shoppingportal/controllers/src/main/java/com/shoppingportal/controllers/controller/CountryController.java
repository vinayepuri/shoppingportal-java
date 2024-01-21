package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.dto.country.CountriesResponse;
import com.ecommerce.controllers.dto.country.CountryDto;
import com.ecommerce.controllers.handler.CountryHandler;
import com.ecommerce.controllers.mappers.CountryControllerMapper;
import com.ecommerce.domain.model.country.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private final CountryHandler countryDriverPort;
    private final CountryControllerMapper countryControllerMapper;

    public CountryController(CountryHandler countryDriverPort, CountryControllerMapper countryControllerMapper) {
        this.countryDriverPort = countryDriverPort;
        this.countryControllerMapper = countryControllerMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<CountriesResponse> getCountries(){
        List<Country> countryList = countryDriverPort.fetchCountries();
        List<CountryDto> countryDtoList = convertCountryListToCountryDtoList(countryList);
        return ResponseEntity.ok(new CountriesResponse(countryDtoList));
    }

    private List<CountryDto> convertCountryListToCountryDtoList(List<Country> countryList) {
        List<CountryDto> countryDtoList = countryList.stream()
                .map(country -> countryControllerMapper.toCountryDto(country))
                .collect(Collectors.toList());
        return countryDtoList;
    }
}
