package com.amex.ordering.service;

import com.amex.ordering.model.Order;

import java.util.Set;

public interface OrderService {
    Order createOrder(Order order);
    Set<Order> getAllOrder();
    Order getOrderById(Long orderId);
}
