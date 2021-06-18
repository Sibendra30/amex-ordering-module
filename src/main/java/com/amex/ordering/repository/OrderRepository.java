package com.amex.ordering.repository;

import com.amex.ordering.model.Order;

public interface OrderRepository {
    Order save(Order order) ;
}
