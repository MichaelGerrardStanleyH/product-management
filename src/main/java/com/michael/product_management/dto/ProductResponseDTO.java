package com.michael.product_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private int id;

    private String name;

    private String description;

    private BigDecimal price;

    private Date createdAt;

}
