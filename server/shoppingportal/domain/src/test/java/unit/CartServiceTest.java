package unit;

import com.ecommerce.domain.model.cart.Cart;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.CartRepositoryPort;
import com.ecommerce.domain.port.drivers.CartDriverPort;
import com.ecommerce.domain.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartDriverPort cartService;

    @Mock
    private AuthenticationGateway authenticationGateway;

    @Mock
    private CartRepositoryPort cartRepository;

    @BeforeEach
    void setup() {
        cartService = new CartService(authenticationGateway, cartRepository);
    }


    @Test
    void whenGetCart_thenRetrieveExistingCart() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        Cart existingCart = new Cart();

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(cartRepository.findCartByUserId(authenticatedUser.getId())).thenReturn(Optional.of(existingCart));

        Cart result = cartService.getCart();

        assertNotNull(result);
        assertEquals(existingCart, result);
    }

    @Test
    void whenGetCartForNewUser_thenRetrieveNewCart() {
        User newUser = new User();
        newUser.setId(2L);

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(newUser);
        when(cartRepository.findCartByUserId(newUser.getId())).thenReturn(Optional.empty());

        Cart result = cartService.getCart();

        assertNotNull(result);
        assertTrue(result.getCartItems().isEmpty());
    }


    @Test
    void givenCart_whenSaveCart_thenCartIsSaved() {
        User authenticatedUser = new User();
        authenticatedUser.setId(1L);
        Cart existingCart = new Cart();
        existingCart.setCartItems(new ArrayList<>());
        Cart newCart = new Cart();

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(authenticatedUser);
        when(cartRepository.findCartByUserId(authenticatedUser.getId())).thenReturn(Optional.of(existingCart));

        cartService.saveCart(newCart);

        verify(cartRepository).save(newCart);

    }

}
