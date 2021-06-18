package com.amex.ordering.service.impl;

import com.amex.ordering.TestUtil;
import com.amex.ordering.model.Order;
import com.amex.ordering.model.OrderItem;
import com.amex.ordering.repository.OrderRepository;
import com.amex.ordering.service.OrderService;
import com.amex.ordering.service.PricingService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderServiceImpl.class)
class OrderServiceImplTest {

    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private PricingService pricingService;

    @BeforeEach
    void setup() {
        orderService = new OrderServiceImpl(orderRepository, pricingService);
    }

    @Test
    void createOrder() {
        Order createOrderReq = TestUtil.getDummyCreateOrderReq();

        Order orderWithPricing = createOrderReq.clone();
        OrderItem expectedOrderItemWithPricing = TestUtil.getDummyOrderItemReq();
        expectedOrderItemWithPricing.setAmount(1.2);
        orderWithPricing.setTotalAmount(1.2);

        Order expectedOrderAfterSave = orderWithPricing.clone();
        expectedOrderAfterSave.setId(100000l);

        when(pricingService.populateOrderingPrices(eq(createOrderReq))).thenReturn(orderWithPricing);
        when(orderRepository.save(eq(orderWithPricing))).thenReturn(expectedOrderAfterSave);

        Order actualOrderAfterSave = orderService.createOrder(TestUtil.getDummyCreateOrderReq());
        assertEquals(expectedOrderAfterSave, actualOrderAfterSave);

        verify(pricingService).populateOrderingPrices(eq(createOrderReq));
        verify(orderRepository).save(eq(orderWithPricing));
    }

    @Test
    void findAllOrders() {
        OrderItem expectedOrderItem = new OrderItem(1001l, 2, 1.2);
        Order expectedOrder = new Order(10000l, 32100l,
                new HashSet<>(Collections.singletonList(expectedOrderItem)), 1.2);
        Set<Order> expectedOrderSet = new HashSet(){{add(expectedOrder);}};
        when(orderRepository.findAllOrders()).thenReturn(expectedOrderSet);

        Set<Order> actualOrders = orderService.getAllOrder();
        assertEquals(expectedOrderSet, actualOrders);
    }

    @Test
    void findOrderByOrderId() {
        OrderItem expectedOrderItem = new OrderItem(1001l, 2, 1.2);
        Order expectedOrder = new Order(10000l, 32100l,
                new HashSet<>(Collections.singletonList(expectedOrderItem)), 1.2);
        when(orderRepository.findOrderById(10000l)).thenReturn(expectedOrder);
        Order actualOrder = orderService.getOrderById(10000l);
        assertEquals(expectedOrder, actualOrder);
    }

}