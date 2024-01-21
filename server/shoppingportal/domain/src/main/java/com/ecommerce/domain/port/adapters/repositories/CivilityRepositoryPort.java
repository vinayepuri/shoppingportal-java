package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.user.Civility;

public interface CivilityRepositoryPort {
    Civility findCivilityById(int Id);
}
