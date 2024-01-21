package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.user.Email;
import com.ecommerce.domain.model.user.Info;
import com.ecommerce.domain.model.user.Profile;

public interface UserDriverPort {

    Profile updateUserInfo(Info info);

    Profile updateUserEmail(Email email);

    Profile getUserProfile();

}
