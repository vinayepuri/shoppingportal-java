package com.ecommerce.controllers.dto.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("country")
    private String name;

    @NotBlank
    private String code;
}
