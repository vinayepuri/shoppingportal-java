//package unit;
//
//import com.ecommerce.exception.CivilityNotFound;
//import com.ecommerce.exception.EmailAlreadyExists;
//import com.ecommerce.exception.EmailNotFound;
//import com.ecommerce.exception.InvalidCredentials;
//import com.ecommerce.model.auth.AuthRequest;
//import com.ecommerce.model.auth.AuthResponse;
//import com.ecommerce.model.auth.SignupRequest;
//import com.ecommerce.model.auth.UserDto;
//import com.ecommerce.model.user.Civility;
//import com.ecommerce.model.user.User;
//import com.ecommerce.port.adapters.repositories.CivilityRepositoryPort;
//import com.ecommerce.port.adapters.repositories.UserRepositoryPort;
//import com.ecommerce.security.JwtUtils;
//import com.ecommerce.security.UserDetailsImpl;
//import com.ecommerce.service.AuthenticationService;
//import com.ecommerce.util.message.ErrorMessages;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthenticationServiceTest {
//
//    @InjectMocks
//    AuthenticationService authenticationService;
//    @Mock
//    private UserRepositoryPort userRepository;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private CivilityRepositoryPort civilityRepository;
//
//    @Mock
//    JwtUtils jwtUtils;
//
//    @Mock
//    private PasswordEncoder encoder;
//
//
//    @Test
//    @DisplayName("Valid token is returned after Authentication Success")
//    void givenUserCredentialsAndUserExists_whenUserIsAuthenticating_thenValidTokenShouldBeReturn() {
//        String email ="test@example.com";
//        String password = "password";
//        AuthRequest authRequest = new AuthRequest(email, password);
//        String role = "USER";
//        String token = "jwt-token";
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(
//                1L, email, password, Collections.singletonList(new SimpleGrantedAuthority(role)));
//        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//        given(userRepository.existsByEmail(email)).willReturn(true);
//        given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password))).willReturn(auth);
//
//        String roleFromUserDetails = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(", "));
//
//        given(jwtUtils.generateToken(email, roleFromUserDetails)).willReturn(token);
//
//        AuthResponse response = authenticationService.signIn(authRequest);
//
//        assertEquals(token, response.getToken());
//    }
//
//    @Test
//    @DisplayName("Valid userDto is returned after Authentication Success")
//    void givenUserCredentialsAndUserExists_whenUserIsAuthenticating_thenValidUserDtoShouldBeReturn() {
//        String email ="test@example.com";
//        String password = "password";
//        AuthRequest authRequest = new AuthRequest(email, password);
//        String role = "USER";
//        String token = "jwt-token";
//        UserDto userDto = new UserDto(1L, role);
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(
//                1L, "test@example.com", "password", Collections.singletonList(new SimpleGrantedAuthority(role)));
//        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, "password", userDetails.getAuthorities());
//        String roleFromUserDetails = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(", "));
//
//        given(userRepository.existsByEmail(authRequest.getEmail())).willReturn(true);
//        given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password))).willReturn(auth);
//
//        given(jwtUtils.generateToken(email, roleFromUserDetails)).willReturn(token);
//
//        AuthResponse response = authenticationService.signIn(authRequest);
//
//        assertEquals(userDto, response.getUser());
//    }
//
//
//    @Test
//    @DisplayName("Throw EmailNotFound when Email does not exist")
//    void givenUserCredentials_whenEmailDoesNotExist_thenThrowEmailNotFound() {
//        String email = "nonexistent@example.com";
//        String password = "password";
//        AuthRequest authRequest = new AuthRequest(email, password);
//
//        given(userRepository.existsByEmail(email)).willReturn(false);
//
//        assertThrows(EmailNotFound.class, () -> authenticationService.signIn(authRequest));
//    }
//
//    @Test
//    @DisplayName("Throw InvalidCredentials when Authentication fails")
//    void givenUserCredentials_whenAuthenticationFails_thenThrowInvalidCredentials() {
//        String email = "test@example.com";
//        String password = "wrongpassword";
//        AuthRequest authRequest = new AuthRequest(email, password);
//
//        given(userRepository.existsByEmail(email)).willReturn(true);
//        given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
//                .willThrow(new InvalidCredentials(ErrorMessages.INVALID_CREDENTIALS) {});
//
//        assertThrows(InvalidCredentials.class, () -> authenticationService.signIn(authRequest));
//    }
//
//    @Test
//    @DisplayName("Successful Signup")
//    void givenSignupRequest_whenAllDetailsAreValid_thenCreateUser() {
//        SignupRequest signupRequest = new SignupRequest(1, "John", "Doe", "test@gmail.com", "password");
//        Civility civility = new Civility(1, "M.");
//
//        given(userRepository.existsByEmail(signupRequest.getEmail())).willReturn(false);
//        given(civilityRepository.findCivilityById(signupRequest.getCivility())).willReturn(Optional.of(civility));
//        given(encoder.encode(signupRequest.getPassword())).willReturn("encodedPassword");
//
//        authenticationService.signup(signupRequest);
//
//        verify(userRepository, times(1)).save(any(User.class));
//        assertDoesNotThrow(() -> authenticationService.signup(signupRequest));
//
//    }
//
//    @Test
//    @DisplayName("Throw EmailAlreadyExists when Email is already in use")
//    void givenSignupRequest_whenEmailAlreadyExists_thenThrowEmailAlreadyExists() {
//        SignupRequest signupRequest = new SignupRequest(1, "John", "Doe", "emailExists@gmail.com", "password");
//
//        given(userRepository.existsByEmail(signupRequest.getEmail())).willReturn(true);
//
//        assertThrows(EmailAlreadyExists.class, () -> authenticationService.signup(signupRequest));
//    }
//
//    @Test
//    @DisplayName("Throw CivilityNotFound when Civility does not exist")
//    void givenSignupRequest_whenCivilityDoesNotExist_thenThrowCivilityNotFound() {
//        SignupRequest signupRequest = new SignupRequest(3, "John", "Doe", "test@gmail.com", "password");
//
//        given(userRepository.existsByEmail(signupRequest.getEmail())).willReturn(false);
//        given(civilityRepository.findCivilityById(signupRequest.getCivility())).willReturn(Optional.empty());
//
//        assertThrows(CivilityNotFound.class, () -> authenticationService.signup(signupRequest));
//    }
//
//}
