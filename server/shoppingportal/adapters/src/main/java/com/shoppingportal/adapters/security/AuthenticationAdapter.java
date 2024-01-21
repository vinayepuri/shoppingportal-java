package com.ecommerce.adapters.security;

import com.ecommerce.adapters.repository.springdata.adapters.UserRepositoryJpaAdapter;
import com.ecommerce.domain.exception.UserNotConnected;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationAdapter implements AuthenticationGateway, UserDetailsService {

    private final UserRepositoryJpaAdapter userRepository;

    public AuthenticationAdapter(UserRepositoryJpaAdapter userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal instanceof UserDetailsImpl){
            Long userId = ((UserDetailsImpl) principal).getId();
            return userRepository.findById(userId);
        } else {
            throw new UserNotConnected(ErrorMessages.USER_NOT_CONNECTED);
        }
    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return UserDetailsImpl.build(user);
    }

}
