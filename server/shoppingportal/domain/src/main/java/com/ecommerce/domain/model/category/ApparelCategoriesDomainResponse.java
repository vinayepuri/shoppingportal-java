package com.ecommerce.domain.model.category;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApparelCategoriesDomainResponse {

    private String gender;

    private List<ApparelCategory> apparelCategories;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setApparelCategories(List<ApparelCategory> apparelCategories) {
        this.apparelCategories = apparelCategories;
    }
}
