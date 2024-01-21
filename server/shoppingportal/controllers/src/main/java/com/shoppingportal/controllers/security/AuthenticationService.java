package com.ecommerce.controllers.security;

import com.ecommerce.adapters.security.UserDetailsImpl;
import com.ecommerce.adapters.security.port.AuthenticationDriverPort;
import com.ecommerce.domain.exception.EmailAlreadyExists;
import com.ecommerce.domain.exception.EmailIsNull;
import com.ecommerce.domain.exception.EmailNotFound;
import com.ecommerce.domain.exception.InvalidCredentials;
import com.ecommerce.domain.model.auth.AuthRequest;
import com.ecommerce.domain.model.auth.AuthResponse;
import com.ecommerce.domain.model.auth.SignupRequest;
import com.ecommerce.domain.model.auth.UserDto;
import com.ecommerce.domain.model.user.Civility;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.repositories.CivilityRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.domain.util.enums.Role;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements AuthenticationDriverPort {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort userRepository;
    private final CivilityRepositoryPort civilityRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepositoryPort userRepository, CivilityRepositoryPort civilityRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.civilityRepository = civilityRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AuthResponse signIn(AuthRequest authRequest) {
        checkUserExistsInDatabase(authRequest.getEmail());
        UserDetailsImpl user = authenticateUser(authRequest.getEmail(), authRequest.getPassword());

        Long id = user.getId();
        String email = user.getEmail();
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        UserDto userDTO = new UserDto(id, role);
        String jwt = jwtUtils.generateToken(email, role);
        return new AuthResponse(userDTO, jwt);
    }

    @Override
    public void signUp(SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyExists(ErrorMessages.EMAIL_ALREADY_EXISTS);
        }

        createUser(signupRequest);
    }

    private UserDetailsImpl authenticateUser(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return (UserDetailsImpl) authentication.getPrincipal();

        } catch(AuthenticationException e) {
            throw  new InvalidCredentials(ErrorMessages.INVALID_CREDENTIALS);
        }
    }

    private void checkUserExistsInDatabase(String email){
        if(email == null){
            throw new EmailIsNull(ErrorMessages.EMAIL_IS_NULL);
        }

        if(!userRepository.existsByEmail(email)){
            throw new EmailNotFound(ErrorMessages.EMAIL_NOT_FOUND);
        }
    }

    private void createUser(SignupRequest signupRequest) {
        User user = signupRequestToUser(signupRequest);
        setUserRole(user);
        encodePassword(signupRequest, user);
        userRepository.save(user);
    }

    public User signupRequestToUser(SignupRequest signup) {
        if ( signup == null ) {
            return null;
        }
        User user = signupToUser(signup);
        return user;
    }

    private void encodePassword(SignupRequest signup, User user) {
        user.setPassword(encoder.encode(signup.getPassword()));
    }

    private void setUserRole(User user) {
        user.setRole(Collections.singleton(Role.USER));
    }

    private User signupToUser(SignupRequest signup) {
        User user = new User();
        user.setFirstName( signup.getFirstName() );
        user.setLastName( signup.getLastName() );
        user.setEmail( signup.getEmail());
        Civility civility = civilityRepository.findCivilityById(signup.getCivility());
        user.setCivility(civility);
        return user;
    }

}
