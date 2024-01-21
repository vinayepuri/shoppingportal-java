package unit;

import com.ecommerce.domain.exception.OrderIsNull;
import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.order.OrderDomainResponse;
import com.ecommerce.domain.model.order.OrderItem;
import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.domain.port.adapters.repositories.AddressRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.OrderRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.domain.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    private OrderService orderService;
    @Mock
    private AuthenticationGateway authenticationGateway;
    @Mock
    private OrderRepositoryPort orderRepository;
    @Mock
    private ProductRepositoryPort productRepository;
    @Mock
    private AddressRepositoryPort addressRepository;


    @BeforeEach
    public void setUp() {
        orderService = new OrderService(authenticationGateway, orderRepository, productRepository, addressRepository);
    }

    @Test
    public void whenSaveValidOrder_thenOrderIsSaved() {
        Order order = new Order();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        assertEquals(order, savedOrder);
        verify(orderRepository).save(order);
    }

    @Test
    public void whenSaveNullOrder_thenThrowException() {
        assertThrows(OrderIsNull.class, () -> orderService.saveOrder(null));
    }

    @Test
    public void whenFetchUserOrders_thenOrdersAreReturned() {
        User user = new User();
        user.setId(1L);
        List<Order> orders = Collections.singletonList(createSampleOrder());

        when(authenticationGateway.getAuthenticatedUser()).thenReturn(user);
        when(orderRepository.findOrderByUserId(user.getId())).thenReturn(orders);

        OrderDomainResponse response = orderService.fetchUserOrders();

        assertEquals(orders, response.getOrders());
        verify(orderRepository).findOrderByUserId(user.getId());
    }

    private Order createSampleOrder() {
        Order order = new Order();
        order.setId(123L);
        order.setDateCreated(new Date());
        order.setTotalPrice(new BigDecimal("100.00"));
        order.setTotalQuantity(2);

        OrderItem item = new OrderItem();
        item.setAmount(new BigDecimal("50.00"));
        item.setQuantity(2);
        item.setProduct(new Product());

        order.setOrderItems(Arrays.asList(item));
        order.setUser(new User());

        return order;
    }
}
