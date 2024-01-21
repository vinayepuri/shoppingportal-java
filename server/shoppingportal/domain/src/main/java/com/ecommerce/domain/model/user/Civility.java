package com.ecommerce.domain.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Civility {

    private int id;

    private String name;

    public Civility(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Civility() {

    }

}
