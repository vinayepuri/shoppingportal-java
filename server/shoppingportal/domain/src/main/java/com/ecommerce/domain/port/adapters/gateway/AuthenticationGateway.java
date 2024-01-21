package com.ecommerce.domain.port.adapters.gateway;

import com.ecommerce.domain.model.user.User;

public interface AuthenticationGateway {
    User getAuthenticatedUser();

}
