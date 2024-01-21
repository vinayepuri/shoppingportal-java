package com.ecommerce.domain.model.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentInfo {

    private Long amount;

    private String currency;



}
