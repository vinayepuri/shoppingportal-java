package com.ecommerce.adapters.repository.springdata.adapters;


import com.ecommerce.adapters.repository.springdata.entity.ProductEntity;
import com.ecommerce.adapters.repository.springdata.repository.ProductJpaRepository;
import com.ecommerce.domain.exception.ProductNotFound;
import com.ecommerce.domain.exception.UserNotFound;
import com.ecommerce.domain.model.cart.Cart;
import com.ecommerce.domain.model.cart.CartItem;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.port.adapters.repositories.CartRepositoryPort;
import com.ecommerce.adapters.repository.springdata.entity.CartEntity;
import com.ecommerce.adapters.repository.springdata.entity.CartItemEntity;
import com.ecommerce.adapters.repository.springdata.entity.UserEntity;
import com.ecommerce.adapters.repository.springdata.mappers.CartItemMapper;
import com.ecommerce.adapters.repository.springdata.mappers.CartMapper;
import com.ecommerce.adapters.repository.springdata.repository.CartJpaRepository;
import com.ecommerce.adapters.repository.springdata.repository.UserJpaRepository;
import com.ecommerce.adapters.security.AuthenticationAdapter;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CartRepositoryJpaAdapter implements CartRepositoryPort {

    private final CartJpaRepository cartJpaRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final UserJpaRepository userJpaRepository;
    private final AuthenticationAdapter authenticationAdapter;

    private final ProductJpaRepository productJpaRepository;

    public CartRepositoryJpaAdapter(CartJpaRepository cartJpaRepository, CartMapper cartMapper, CartItemMapper cartItemMapper, UserJpaRepository userJpaRepository, AuthenticationAdapter authenticationAdapter, ProductJpaRepository productJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.userJpaRepository = userJpaRepository;
        this.authenticationAdapter = authenticationAdapter;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Optional<Cart> findCartByUserId(long id) {
        return cartJpaRepository.findCartByUserEntityId(id)
                .map(this::mapCartEntityToCart);
    }

    private Cart mapCartEntityToCart(CartEntity cartEntity){
        Cart cart = cartMapper.toCart(cartEntity);
        addCartItemToCart(cartEntity, cart);
        return cart;
    }

    private void addCartItemToCart(CartEntity cartEntity, Cart cart) {
        cartEntity.getCartItemEntityList().forEach(cartItemEntity -> {
            CartItem cartItem = cartItemMapper.toCartItem(cartItemEntity);
            cart.addCartItem(cartItem);
        });
    }

    @Override
    public void save(Cart cart) {
        CartEntity cartEntity = new CartEntity();
        mapCartToCartEntity(cart, cartEntity);
        cartJpaRepository.save(cartEntity);
    }

    private void mapCartToCartEntity(Cart cart, CartEntity cartEntity) {
        cartEntity.setId(cart.getId());
        cartEntity.setTotalPrice(cart.getTotalPrice());
        cartEntity.setTotalQuantity(cart.getTotalQuantity());
        setUserEntity(cartEntity);
        setCartItemEntityList(cart, cartEntity);
    }

    private void setCartItemEntityList(Cart cart, CartEntity cartEntity) {
        List<CartItemEntity> cartItemEntityList = cart.getCartItems().stream()
                .map(cartItem -> {
                    CartItemEntity cartItemEntity = cartItemMapper.toCartItemEntity(cartItem);
                    cartItemEntity.setCartEntity(cartEntity);
                    setProductEntity(cartItemEntity, cartItem);
                    return cartItemEntity;
                })
                .collect(Collectors.toList());
        cartEntity.setCartItemEntityList(cartItemEntityList);
    }

    private void setProductEntity(CartItemEntity cartItemEntity, CartItem cartItem) {
        ProductEntity productEntity = productJpaRepository.findById(cartItem.getProduct().getId())
                .orElseThrow(()-> new ProductNotFound(ErrorMessages.PRODUCT_NOT_FOUND));
        cartItemEntity.setProductEntity(productEntity);
    }

    private void setUserEntity(CartEntity cartEntity) {
        User user = authenticationAdapter.getAuthenticatedUser();
        UserEntity userEntity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFound(ErrorMessages.USER_NOT_FOUND));
        cartEntity.setUserEntity(userEntity);
    }


}
