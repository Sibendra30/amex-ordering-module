package com.amex.ordering.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @NotNull
    private Long itemId;

    private String itemName;

    @NotNull
    @Min(value = 1)
    private int qty;

    private double amount;

    public OrderItem(Long itemId, int qty, double amount) {
        this.itemId = itemId;
        this.qty = qty;
        this.amount = amount;
    }

    @SneakyThrows
    public OrderItem clone() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(this);
        return objectMapper.readValue(jsonStr, OrderItem.class);
    }
}
