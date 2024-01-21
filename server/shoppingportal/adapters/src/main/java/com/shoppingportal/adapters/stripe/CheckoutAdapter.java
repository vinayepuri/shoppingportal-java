package com.ecommerce.adapters.stripe;


import com.ecommerce.adapters.stripe.port.CheckoutDriverPort;
import com.ecommerce.domain.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.domain.model.payment.PaymentInfo;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class CheckoutAdapter implements CheckoutDriverPort {

    protected UserRepositoryPort userRepository;
    protected StripeService stripeService;

    public CheckoutAdapter(UserRepositoryPort userRepository, StripeService stripeService) {
        this.userRepository = userRepository;
        this.stripeService = stripeService;
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setAmount(paymentInfo.getAmount())
                        .setCurrency(paymentInfo.getCurrency())
                        .addPaymentMethodType("card")
                        .build();

        return stripeService.createPaymentIntent(params);
    }



}

