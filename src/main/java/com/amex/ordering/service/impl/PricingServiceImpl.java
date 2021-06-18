package com.amex.ordering.service.impl;

import com.amex.ordering.model.CatalogItem;
import com.amex.ordering.model.Order;
import com.amex.ordering.model.OrderItem;
import com.amex.ordering.repository.CatalogRepository;
import com.amex.ordering.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PricingServiceImpl implements PricingService {
    private CatalogRepository catalogRepository;

    public PricingServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Order populateOrderingPrices(Order order) {
        Set<OrderItem> orderItems = order.getItems();

        final double[] totalOrderAmount = {0};
        orderItems = orderItems.stream().map(item -> {
            CatalogItem catalogItem = catalogRepository.getCatalogItemById(item.getItemId());
            double orderItemTotal = calculatePriceWithOffer(item.getQty(), catalogItem);
            totalOrderAmount[0] = totalOrderAmount[0] + orderItemTotal;
           return new OrderItem(item.getItemId(), item.getQty(), orderItemTotal);
        }).collect(Collectors.toSet());
        order.setItems(orderItems);
        order.setTotalAmount(totalOrderAmount[0]);
        return order;
    }

    protected double calculatePriceWithOffer(int qty, CatalogItem catalogItem) {
        double amount = 0;
        if (catalogItem.getOffers() != null) {
            int productWithOffer = qty / catalogItem.getOffers().getOfferItemCount();
            int productWithoutOffer = qty % catalogItem.getOffers().getOfferItemCount();
            amount = productWithOffer * catalogItem.getOffers().getOfferItemPayFor() * catalogItem.getRate()
                    + productWithoutOffer * catalogItem.getRate();
        } else {
            amount = qty * catalogItem.getRate();
        }
        return amount;
    }
}
