package com.michael.product_management.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductRequestDTO {

    private String name;

    private String description;

    private BigDecimal price;
}
