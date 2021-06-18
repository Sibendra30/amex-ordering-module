package com.amex.ordering.service.impl;

import com.amex.ordering.model.Order;
import com.amex.ordering.repository.OrderRepository;
import com.amex.ordering.service.OrderService;
import com.amex.ordering.service.PricingService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PricingService pricingService;

    public OrderServiceImpl(OrderRepository orderRepository, PricingService pricingService) {
        this.orderRepository = orderRepository;
        this.pricingService = pricingService;
    }

    @Override
    public Order createOrder(Order order) {
        Order orderWithPricing = pricingService.populateOrderingPrices(order);
        return orderRepository.save(orderWithPricing);
    }
}
