package com.amex.ordering.repository;

import com.amex.ordering.model.Order;

import java.util.Set;

public interface OrderRepository {
    Order save(Order order) ;
    Set<Order> findAllOrders();
    Order findOrderById(Long orderId);
}
