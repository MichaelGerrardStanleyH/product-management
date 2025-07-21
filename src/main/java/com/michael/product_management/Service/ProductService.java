package com.michael.product_management.Service;

import com.michael.product_management.dto.ProductRequestDTO;
import com.michael.product_management.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService{

    List<Product> getAll();

    Product getById(Integer id);

    Product create(ProductRequestDTO dto);

    Product update(Integer id, ProductRequestDTO dto);

    void delete(Integer id);

    List<Product> getByName(String name);

    List<Product> getByPriceBetween(BigDecimal min, BigDecimal max);

}
