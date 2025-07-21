package com.michael.product_management.repository;

import com.michael.product_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findByName(String name);

    Optional<List<Product>> findByPriceBetween(BigDecimal min, BigDecimal max);

}
