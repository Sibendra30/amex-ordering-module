package com.amex.ordering;

import com.amex.ordering.model.Order;
import com.amex.ordering.model.OrderItem;

import java.util.Collections;
import java.util.HashSet;

public final class TestUtil {

    public static Order getDummyCreateOrderReq() {
        Order order = new Order();
        order.setCustomerId(12345L);
        order.setItems(new HashSet<>(Collections.singletonList(getDummyOrderItemReq())));
        return order;
    }

    public static OrderItem getDummyOrderItemReq() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(1001l);
        orderItem.setQty(2);
        return orderItem;
    }

    public static Order getDummyCreateOrderRes() {
        Order order = getDummyCreateOrderReq();
        Order expectedOrder = order.clone();
        OrderItem expectedOrderItem = getDummyOrderItemReq().clone();
        expectedOrderItem.setAmount(1.2);
        expectedOrder.setItems(new HashSet<>(Collections.singletonList(expectedOrderItem)));
        expectedOrder.setTotalAmount(1.2);
        expectedOrder.setId(100000l);
        return expectedOrder;
    }
}
