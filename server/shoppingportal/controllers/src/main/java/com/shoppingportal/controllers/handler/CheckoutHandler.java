package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.UserRepositoryJpaAdapter;
import com.ecommerce.adapters.stripe.CheckoutAdapter;
import com.ecommerce.adapters.stripe.StripeService;
import org.springframework.stereotype.Service;

@Service
public class CheckoutHandler extends CheckoutAdapter {


    public CheckoutHandler(UserRepositoryJpaAdapter userRepository, StripeService stripeService) {
        super(userRepository, stripeService);
    }

}
