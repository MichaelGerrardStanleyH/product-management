package com.michael.product_management.controller;

import com.michael.product_management.Service.ProductService;
import com.michael.product_management.dto.ProductRequestDTO;
import com.michael.product_management.dto.ProductResponseDTO;
import com.michael.product_management.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct(){
        List<Product> productList = this.productService.getAll();

        List<ProductResponseDTO> responseDTOList = productList.stream().map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        )).toList();


        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Integer id){
        Product product = this.productService.getById(id);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto){
        Product product = productService.create(dto);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductRequestDTO dto){
        Product product = this.productService.update(id, dto);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") Integer id){
        this.productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}







