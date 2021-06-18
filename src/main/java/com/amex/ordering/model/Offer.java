package com.amex.ordering.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private Long id;
    private String offerType;
    private int offerItemCount;
    private int offerItemPayFor;
}
