package com.amex.ordering.api;

import com.amex.ordering.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
public interface OrderApi {

    @PostMapping
    ResponseEntity<Order> createOrder(@Validated @RequestBody Order requestBody);
}
