package com.ecommerce.domain.model.country;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Country {

    private int id;

    private String name;

    private String code;

    public Country() {
    }


}
