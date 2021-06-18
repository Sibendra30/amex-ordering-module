package com.amex.ordering.controller;

import com.amex.ordering.error.OrderNotFoundException;
import com.amex.ordering.model.Order;
import com.amex.ordering.model.OrderItem;
import com.amex.ordering.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void createOrder() throws Exception {
        Order order = new Order();
        order.setCustomerId(12345L);
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(1001l);
        orderItem.setQty(2);
        order.setItems(new HashSet<>(Collections.singletonList(orderItem)));

        Order expectedOrder = order.clone();
        OrderItem expectedOrderItem = orderItem.clone();
        expectedOrderItem.setAmount(1.2);
        expectedOrder.setItems(new HashSet<>(Collections.singletonList(expectedOrderItem)));
        expectedOrder.setTotalAmount(1.2);
        expectedOrder.setId(100000l);

        when(orderService.createOrder(eq(order))).thenReturn(expectedOrder);

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount", Matchers.is(1.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].itemId", Matchers.is(1001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].qty", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].amount", Matchers.is(1.2)))
                .andReturn();

        verify(orderService).createOrder(eq(order));
    }

    @Test
    void createOrder_emptyOrderItem() throws Exception {
        Order order = new Order();
        order.setCustomerId(12345L);;
        order.setItems(Collections.emptySet());

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn();
    }

    @Test
    void createOrder_nullOrderItem() throws Exception {
        Order order = new Order();
        order.setCustomerId(12345L);

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn();
    }

    @Test
    void createOrder_nullCustomerId() throws Exception {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(1001l);
        orderItem.setQty(2);
        order.setItems(new HashSet<>(Collections.singletonList(orderItem)));

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn();
    }

    @Test
    void createOrder_invalidOrderItem() throws Exception {
        Order order = new Order();
        order.setCustomerId(12345L);
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(null);
        orderItem.setQty(2);
        order.setItems(new HashSet<>(Collections.singletonList(orderItem)));

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn();
    }

    @Test
    void createOrder_zeroQtyOrderItem() throws Exception {
        Order order = new Order();
        order.setCustomerId(12345L);
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(1001l);
        orderItem.setQty(0);
        order.setItems(new HashSet<>(Collections.singletonList(orderItem)));

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn();
    }

    @Test
    void getAllOrder() throws Exception {
        OrderItem expectedOrderItem = new OrderItem(1001l, 2, 1.2);
        Order expectedOrder = new Order(10000l, 32100l,
                new HashSet<>(Collections.singletonList(expectedOrderItem)), 1.2);
        Set<Order> expectedOrderSet = new HashSet(){{add(expectedOrder);}};

        when(orderService.getAllOrder()).thenReturn(expectedOrderSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/order"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].customerId", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].totalAmount", Matchers.is(1.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].items[0].itemId", Matchers.is(1001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].items[0].qty", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].items[0].amount", Matchers.is(1.2)))
                .andReturn();
    }

    @Test
    void getOrderById() throws Exception {
        OrderItem expectedOrderItem = new OrderItem(1001l, 2, 1.2);
        Order expectedOrder = new Order(10000l, 32100l,
                new HashSet<>(Collections.singletonList(expectedOrderItem)), 1.2);

        when(orderService.getOrderById(10000l)).thenReturn(expectedOrder);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/10000"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount", Matchers.is(1.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].itemId", Matchers.is(1001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].qty", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].amount", Matchers.is(1.2)))
                .andReturn();
    }

    @Test
    void getOrderById_invalidOrder() throws Exception {
        when(orderService.getOrderById(10000l)).thenThrow(new OrderNotFoundException(10000l));

        mockMvc.perform(MockMvcRequestBuilders.get("/order/10000"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.is("invalidOrderId")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Order not found for orderId#10000")))
                .andReturn();
    }

    @Test
    void getOrderById_genericException() throws Exception {
        when(orderService.getOrderById(10000l)).thenThrow(new RuntimeException("general exception"));

        mockMvc.perform(MockMvcRequestBuilders.get("/order/10000"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.is("internalServerError")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Something went wrong. Please contact system admin")))
                .andReturn();
    }
}
