package com.ecommerce.domain.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Info {

    private Civility civility;

    private String firstName;

    private String lastName;

    public Info(Civility civility, String firstName, String lastName) {
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Info() {

    }
}
