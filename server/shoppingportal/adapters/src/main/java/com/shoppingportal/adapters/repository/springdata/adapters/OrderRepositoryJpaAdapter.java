package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.adapters.repository.springdata.entity.*;
import com.ecommerce.adapters.repository.springdata.mappers.OrderItemMapper;
import com.ecommerce.adapters.repository.springdata.mappers.OrderMapper;
import com.ecommerce.adapters.repository.springdata.repository.AddressJpaRepository;
import com.ecommerce.adapters.repository.springdata.repository.OrderJpaRepository;
import com.ecommerce.adapters.repository.springdata.repository.ProductJpaRepository;
import com.ecommerce.adapters.repository.springdata.repository.UserJpaRepository;
import com.ecommerce.domain.exception.AddressNotFound;
import com.ecommerce.domain.exception.ProductNotFound;
import com.ecommerce.domain.exception.UserNotFound;
import com.ecommerce.domain.model.address.Address;
import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.order.OrderItem;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.repositories.OrderRepositoryPort;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryJpaAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;
    private final UserJpaRepository userJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderRepositoryJpaAdapter(OrderJpaRepository orderJpaRepository, OrderMapper orderMapper, UserJpaRepository userJpaRepository, AddressJpaRepository addressJpaRepository, ProductJpaRepository productJpaRepository, OrderItemMapper orderItemMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderMapper = orderMapper;
        this.userJpaRepository = userJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public List<Order> findOrderByUserId(long id) {
        List<OrderEntity> orderEntities = orderJpaRepository.findOrderByUserEntityId(id);
        return orderMapper.toOrders(orderEntities);
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        setUserEntity(orderEntity, order.getUser());
        setAddressEntity(orderEntity, order.getAddress());
        setOrderItemEntities(orderEntity, order.getOrderItems());
        OrderEntity savedOrderEntity = orderJpaRepository.save(orderEntity);
        return orderMapper.toOrder(savedOrderEntity);
    }

    public void setOrderItemEntities(OrderEntity orderEntity, List<OrderItem> orderItems) {
        List<OrderItemEntity> orderItemEntities = mapOrderItemsToOrderItemEntities(orderEntity, orderItems);
        orderEntity.setOrderItemEntityList(orderItemEntities);
    }

    public List<OrderItemEntity> mapOrderItemsToOrderItemEntities(OrderEntity orderEntity, List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> mapOrderItemToOrderItemEntity(orderEntity, orderItem))
                .collect(Collectors.toList());
    }

    public OrderItemEntity mapOrderItemToOrderItemEntity(OrderEntity orderEntity, OrderItem orderItem) {
        OrderItemEntity orderItemEntity = orderItemMapper.orderItemToOrderItemEntity(orderItem);
        orderItemEntity.setOrderEntity(orderEntity);
        orderItemEntity.setProductEntity(findProductById(orderItem.getProduct().getId()));
        return orderItemEntity;
    }

    private ProductEntity findProductById(Long productId){
        return  productJpaRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFound(ErrorMessages.PRODUCT_NOT_FOUND));
    }

    private void setAddressEntity(OrderEntity orderEntity, Address address) {
        AddressEntity addressEntity = addressJpaRepository.findById(address.getId())
                .orElseThrow(()-> new AddressNotFound(ErrorMessages.ADDRESS_NOT_FOUND));
        orderEntity.setAddressEntity(addressEntity);
    }

    private void setUserEntity(OrderEntity orderEntity, User user) {
        UserEntity userEntity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFound(ErrorMessages.USER_NOT_FOUND));
        orderEntity.setUserEntity(userEntity);
    }

}
