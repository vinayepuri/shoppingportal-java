package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.domain.exception.CivilityNotFound;
import com.ecommerce.domain.exception.UserNotFound;
import com.ecommerce.domain.exception.UserNotFoundByEmail;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.adapters.repository.springdata.mappers.UserMapper;
import com.ecommerce.adapters.repository.springdata.entity.CivilityEntity;
import com.ecommerce.adapters.repository.springdata.entity.UserEntity;
import com.ecommerce.adapters.repository.springdata.repository.CivilityJpaRepository;
import com.ecommerce.adapters.repository.springdata.repository.UserJpaRepository;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final CivilityJpaRepository civilityJpaRepository;
    private final UserMapper userMapper;

    public UserRepositoryJpaAdapter(UserJpaRepository userJpaRepository, CivilityJpaRepository civilityJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.civilityJpaRepository = civilityJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundByEmail(ErrorMessages.USER_NOT_FOUND_WITH_EMAIL + email, email));;
        return userMapper.toUser(userEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        setCivilityEntity(user, userEntity);
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);
        return userMapper.toUser(savedUserEntity);
    }

    private void setCivilityEntity(User user, UserEntity userEntity) {
        CivilityEntity civilityEntity = civilityJpaRepository.findCivilityEntityById(user.getCivility().getId())
                .orElseThrow(() -> new CivilityNotFound(ErrorMessages.CIVILITY_NOT_FOUND));
        userEntity.setCivilityEntity(civilityEntity);
    }

    @Override
    public User findById(Long userId) {
        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(()-> new UserNotFound(ErrorMessages.USER_NOT_FOUND));
        return userMapper.toUser(userEntity);
    }

}
