package com.amex.ordering.api;

import com.amex.ordering.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.Set;

@RequestMapping("/order")
public interface OrderApi {

    @PostMapping
    ResponseEntity<Order> createOrder(@Validated @RequestBody Order requestBody);

    @GetMapping
    ResponseEntity<Set<Order>> getOrders();

    @GetMapping("/{orderId}")
    ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId);
}
