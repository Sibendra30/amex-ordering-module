package com.amex.ordering.repository;

import com.amex.ordering.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    Map<Long, Order> orderStorage = new HashMap<>();
    private long orderNumSeq = 10000;

    public Order save(Order order) {
        Long orderId = orderNumSeq ++;
        order.setId(orderId);
        orderStorage.put(orderId, order);
        return order;
    }
}
