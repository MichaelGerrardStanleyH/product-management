package com.michael.product_management.Service.impl;


import com.michael.product_management.Service.ProductService;
import com.michael.product_management.dto.ProductRequestDTO;
import com.michael.product_management.entity.Product;
import com.michael.product_management.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("no product with id " + id)
        );
    }

    @Override
    public Product create(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCreatedAt(new Date());

        return this.productRepository.save(product);
    }

    @Override
    public Product update(Integer id, ProductRequestDTO dto) {
        Product existProduct = this.productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("no product with id " + id)
        );

        existProduct.setName(dto.getName());
        existProduct.setPrice(dto.getPrice());
        existProduct.setDescription(dto.getDescription());

        return this.productRepository.save(existProduct);
    }

    @Override
    public void delete(Integer id) {
        Product existProduct = this.productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("no product with id " + id)
        );

        this.productRepository.delete(existProduct);
    }

    @Override
    public List<Product> getByName(String name) {
        return this.productRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("no product with name " + name)
        );
    }

    @Override
    public List<Product> getByPriceBetween(BigDecimal min, BigDecimal max) {
        return this.productRepository.findByPriceBetween(min, max).orElseThrow(
                () -> new EntityNotFoundException("no product with range price " + min + " to " + max)
        );
    }
}
