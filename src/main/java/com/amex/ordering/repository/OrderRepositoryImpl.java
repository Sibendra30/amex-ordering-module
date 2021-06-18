package com.amex.ordering.repository;

import com.amex.ordering.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    Map<Long, Order> orderStorage = new HashMap<>();
    private long orderNumSeq = 10000;

    @Override
    public Order save(Order order) {
        Long orderId = orderNumSeq ++;
        order.setId(orderId);
        orderStorage.put(orderId, order);
        return order;
    }

    @Override
    public Set<Order> findAllOrders() {
        return new HashSet<>(this.orderStorage.values());
    }

    @Override
    public Order findOrderById(Long orderId) {
        return this.orderStorage.get(orderId);
    }
}
