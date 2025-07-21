package com.michael.product_management.controller;

import com.michael.product_management.Service.ProductService;
import com.michael.product_management.dto.BaseResponse;
import com.michael.product_management.dto.ProductRequestDTO;
import com.michael.product_management.dto.ProductResponseDTO;
import com.michael.product_management.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<BaseResponse<List<ProductResponseDTO>>> getAllProduct(){
        List<Product> productList = this.productService.getAll();

        List<ProductResponseDTO> responseDTOList = productList.stream().map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        )).toList();

        BaseResponse<List<ProductResponseDTO>> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.OK.value(),
                responseDTOList
        );


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> getProductById(@PathVariable("id") Integer id){
        Product product = this.productService.getById(id);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );

        BaseResponse<ProductResponseDTO> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.OK.value(),
                productResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> createProduct(@Valid @RequestBody ProductRequestDTO dto){
        Product product = productService.create(dto);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );

        BaseResponse<ProductResponseDTO> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.CREATED.value(),
                productResponseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody ProductRequestDTO dto){
        Product product = this.productService.update(id, dto);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );

        BaseResponse<ProductResponseDTO> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.OK.value(),
                productResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<?>> deleteProduct(@PathVariable("id") Integer id){
        this.productService.delete(id);

        BaseResponse<String> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.NO_CONTENT.value(),
                "Delete successfully"
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/names")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity< BaseResponse<List<ProductResponseDTO>>> searchByNames(@RequestParam("name") String name){
        List<Product> productList = this.productService.getByName(name);

        List<ProductResponseDTO> responseDTOList = productList.stream().map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        )).toList();

        BaseResponse<List<ProductResponseDTO>> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.OK.value(),
                responseDTOList
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/prices-between")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<BaseResponse<List<ProductResponseDTO>>> searchByPriceRange(
            @RequestParam("min") BigDecimal min,
            @RequestParam("max") BigDecimal max
    ){
        List<Product> productList = this.productService.getByPriceBetween(min, max);

        List<ProductResponseDTO> responseDTOList = productList.stream().map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        )).toList();

        BaseResponse<List<ProductResponseDTO>> response = new BaseResponse<>(
                Boolean.TRUE,
                HttpStatus.OK.value(),
                responseDTOList
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}







