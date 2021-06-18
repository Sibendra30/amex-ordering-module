package com.amex.ordering.service.impl;

import com.amex.ordering.TestUtil;
import com.amex.ordering.model.CatalogItem;
import com.amex.ordering.model.Order;
import com.amex.ordering.model.OrderItem;
import com.amex.ordering.repository.CatalogRepository;
import com.amex.ordering.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PricingServiceImplTest {

    @MockBean
    private CatalogRepository catalogRepository;

    private PricingService pricingService;

    @BeforeEach
    void setup() {
        pricingService = new PricingServiceImpl(catalogRepository);
    }

    @Test
    void populateOrderingPrices() {
        Order createOrderReq = TestUtil.getDummyCreateOrderReq();

        Order orderWithPricing = createOrderReq.clone();
        OrderItem expectedOrderItemWithPricing = TestUtil.getDummyOrderItemReq();
        expectedOrderItemWithPricing.setAmount(1.2);
        orderWithPricing.setItems(new HashSet<>(Collections.singletonList(expectedOrderItemWithPricing)));
        orderWithPricing.setTotalAmount(1.2);

       when(catalogRepository.getCatalogItemById(1001l))
               .thenReturn(new CatalogItem(1001l, "Apple", "Fresh Apple", 0.6));

        Order actualOrderWithPricing = pricingService.populateOrderingPrices(createOrderReq);
        assertEquals(orderWithPricing, actualOrderWithPricing);
    }

}