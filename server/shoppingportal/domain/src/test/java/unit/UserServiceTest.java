package unit;

import com.ecommerce.domain.model.user.*;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private AuthenticationGateway authenticationGateway;

    @BeforeEach
    void setup(){
        userService = new UserService(userRepository, authenticationGateway);
    }

    @Test
    public void whenUpdateUserInfo_thenUserInfoIsUpdated() {
        Civility civility = new Civility(1, "Mr");
        User authenticatedUser = new User();
        Info info = new Info(civility, "John", "Doe");
        authenticatedUser.setFirstName(info.getFirstName());

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(userRepository.save(authenticatedUser)).thenReturn(authenticatedUser);

        Profile updatedProfile = userService.updateUserInfo(info);

        assertNotNull(updatedProfile);
        assertEquals(info.getFirstName(), updatedProfile.getInfo().getFirstName());
        assertEquals(info.getLastName(), updatedProfile.getInfo().getLastName());
        assertEquals(info.getCivility(), updatedProfile.getInfo().getCivility());
    }

    @Test
    public void whenUpdateUserEmail_thenEmailIsUpdated() {
        User authenticatedUser = new User();
        Email email = new Email("john.doe@example.com");
        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(userRepository.save(any(User.class))).thenReturn(authenticatedUser);

        Profile updatedProfile = userService.updateUserEmail(email);

        assertNotNull(updatedProfile);
        assertEquals(email.getEmail(), updatedProfile.getEmail().getEmail());
    }
}
