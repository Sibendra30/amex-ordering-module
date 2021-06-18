package com.amex.ordering.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    @Size(min = 1)
    @Valid
    private Set<OrderItem> items;
    private double totalAmount;

    @SneakyThrows
    public Order clone() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(this);
        return objectMapper.readValue(jsonStr, Order.class);
    }
}
