package com.amex.ordering.error;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {

    private Long orderId;

    public OrderNotFoundException(Long orderId) {
        super("Order not found with orderId#" + orderId);
        this.orderId = orderId;
    }
}
