package com.amex.ordering.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItem {
    private Long id;
    private String name;
    private String description;
    private Double rate;
}
