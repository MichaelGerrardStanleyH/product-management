package com.michael.product_management.Service;

import com.michael.product_management.Service.impl.ProductServiceImpl;
import com.michael.product_management.dto.ProductRequestDTO;
import com.michael.product_management.entity.Product;
import com.michael.product_management.repository.ProductRepository;
import com.michael.product_management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void getAll() {
        List<Product> productList = List.of(
                new Product(1, "item", "item baru", new BigDecimal(1), new Date())
        );

        when(this.productRepository.findAll()).thenReturn(productList);

        var result = this.productService.getAll();

        assertEquals(productList.get(0).getId(), result.get(0).getId());
        assertEquals(productList.get(0).getName(), result.get(0).getName());
        assertEquals(productList.get(0).getPrice(), result.get(0).getPrice());
        assertEquals(productList.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    void getById() {
        Product product = new Product(1, "item", "item baru", new BigDecimal(1), new Date());

        when(this.productRepository.findById(1)).thenReturn(Optional.of(product));

        var result = this.productService.getById(1);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getDescription(), result.getDescription());
    }

    @Test
    void create() {
        Product product = new Product(1, "item", "item baru", new BigDecimal(1), new Date());

        ProductRequestDTO dto = new ProductRequestDTO("item", "item baru", new BigDecimal(1));

        when(this.productRepository.save(any())).thenReturn(product);

        var result = this.productService.create(dto);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getDescription(), result.getDescription());
    }

    @Test
    void update() {
        Product product = new Product(1, "item", "item baru", new BigDecimal(1), new Date());

        ProductRequestDTO dto = new ProductRequestDTO("item", "item baru", new BigDecimal(1));


        when(this.productRepository.findById(1)).thenReturn(Optional.of(product));
        when(this.productRepository.save(any())).thenReturn(product);

        var result = this.productService.update(1, dto);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getDescription(), result.getDescription());
    }

    @Test
    void delete() {
        Product product = new Product(1, "item", "item baru", new BigDecimal(1), new Date());

        when(this.productRepository.findById(1)).thenReturn(Optional.of(product));

        this.productService.delete(1);

        verify(this.productRepository, times(1)).delete(product);
    }

    @Test
    void getByName() {
        List<Product> productList = List.of(
                new Product(1, "item", "item baru", new BigDecimal(1), new Date())
        );


        when(this.productRepository.findByName(any())).thenReturn(Optional.of(productList));

        var result = this.productService.getByName("test");

        assertEquals(productList.get(0).getId(), result.get(0).getId());
        assertEquals(productList.get(0).getName(), result.get(0).getName());
        assertEquals(productList.get(0).getPrice(), result.get(0).getPrice());
        assertEquals(productList.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    void getByPriceRange() {
        List<Product> productList = List.of(
                new Product(1, "item", "item baru", new BigDecimal(1), new Date())
        );


        when(this.productRepository.findByPriceBetween(any(), any())).thenReturn(Optional.of(productList));

        var result = this.productService.getByPriceBetween(new BigDecimal(1), new BigDecimal(2));

        assertEquals(productList.get(0).getId(), result.get(0).getId());
        assertEquals(productList.get(0).getName(), result.get(0).getName());
        assertEquals(productList.get(0).getPrice(), result.get(0).getPrice());
        assertEquals(productList.get(0).getDescription(), result.get(0).getDescription());
    }
}