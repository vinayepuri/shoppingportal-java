package com.ecommerce.domain.service;

import com.ecommerce.domain.model.user.Email;
import com.ecommerce.domain.model.user.Info;
import com.ecommerce.domain.model.user.Profile;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.domain.port.drivers.UserDriverPort;

public class UserService implements UserDriverPort {

    protected UserRepositoryPort userRepository;
    protected AuthenticationGateway authenticationGateway;

    public UserService(UserRepositoryPort userRepository, AuthenticationGateway authenticationGateway) {
        this.userRepository = userRepository;
        this.authenticationGateway = authenticationGateway;
    }

    @Override
    public Profile updateUserInfo(Info info) {
        User savedUser = updateAuthenticatedUser(info);
        return setProfileWithInfoFromUpdatedUser(savedUser);
    }

    private Profile setProfileWithInfoFromUpdatedUser(User savedUser) {
        Profile profile = new Profile();
        Info info = new Info();
        mapInfoWithInfoFromUser(savedUser, info);
        profile.setInfo(info);
        return profile;
    }

    private void mapInfoWithInfoFromUser(User savedUser, Info infoAfterUpdate) {
        infoAfterUpdate.setCivility(savedUser.getCivility());
        infoAfterUpdate.setFirstName(savedUser.getFirstName());
        infoAfterUpdate.setLastName(savedUser.getLastName());
    }

    private User updateAuthenticatedUser(Info info) {
        User user = this.getAuthenticatedUser();
        updateUser(info, user);
        return userRepository.save(user);
    }

    private void updateUser(Info info, User user) {
        user.setCivility(info.getCivility());
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
    }

    @Override
    public Profile updateUserEmail(Email email) {
        User savedUser = updateUserWithEmail(email);
        Profile profile = setPorfileWithEmailFromUser(savedUser);
        return profile;
    }

    private Profile setPorfileWithEmailFromUser(User savedUser) {
        Profile profile = new Profile();
        profile.setEmail(new Email(savedUser.getEmail()));
        return profile;
    }

    private User updateUserWithEmail(Email email) {
        User user = this.getAuthenticatedUser();
        user.setEmail(email.getEmail());
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public Profile getUserProfile() {

        Info info = User.toInfo(this.getAuthenticatedUser());
        Email email = new Email(this.getAuthenticatedUser().getEmail());

        return new Profile(info, email);
    }

    public User getAuthenticatedUser(){
        return authenticationGateway.getAuthenticatedUser();
    }


}

