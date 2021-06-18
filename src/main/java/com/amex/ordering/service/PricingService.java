package com.amex.ordering.service;

import com.amex.ordering.model.Order;

public interface PricingService {

    Order populateOrderingPrices(Order order);
}
