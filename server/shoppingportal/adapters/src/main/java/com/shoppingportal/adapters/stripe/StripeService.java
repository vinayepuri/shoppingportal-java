package com.ecommerce.adapters.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.key.secret}")
    private String secretKey;

    public PaymentIntent createPaymentIntent(PaymentIntentCreateParams params) throws StripeException {
        // Direct call to Stripe API
        Stripe.apiKey = secretKey;
        return PaymentIntent.create(params);
    }
}
