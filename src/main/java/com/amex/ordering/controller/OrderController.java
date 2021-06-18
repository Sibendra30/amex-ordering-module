package com.amex.ordering.controller;

import com.amex.ordering.api.OrderApi;
import com.amex.ordering.model.Order;
import com.amex.ordering.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class OrderController implements OrderApi {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<Order> createOrder(Order requestBody) {
        return new ResponseEntity<>(orderService.createOrder(requestBody), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Set<Order>> getOrders() {
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> getOrderById(Long orderId) {
       return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

}
