package com.ecommerce.domain.model.user;

import com.ecommerce.domain.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;

    private Civility civility;

    private String firstName;

    private String lastName;

    private String email;

    private Set<Role> role = new HashSet<>();

    private String password;

    public static Info toInfo(User user) {
        Info info = new Info();
        info.setCivility(user.getCivility());
        info.setFirstName(user.getFirstName());
        info.setLastName(user.getLastName());
        return info;
    }

    public User() {
    }

}
