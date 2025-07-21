package com.michael.product_management.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.product_management.Service.ProductService;
import com.michael.product_management.dto.BaseResponse;
import com.michael.product_management.dto.ProductRequestDTO;
import com.michael.product_management.dto.ProductResponseDTO;
import com.michael.product_management.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockitoBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAllProducts() throws Exception{

        BaseResponse<List<ProductResponseDTO>> responseList = new BaseResponse<>(
                true,
                200,
                List.of(new ProductResponseDTO(
                        1, "item", "item baru", new BigDecimal(1), new Date()
                ))
        );

        when(this.productService.getAll()).thenReturn(List.of(new Product(
                1, "item", "item baru", new BigDecimal(1), new Date()
        )));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect( result -> {
                    BaseResponse<List<ProductResponseDTO>> response = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<List<ProductResponseDTO>>>() {
                    });

                    assertEquals(responseList.getData().get(0).getId(), response.getData().get(0).getId());
                    assertEquals(responseList.getData().get(0).getName(), response.getData().get(0).getName());
                    assertEquals(responseList.getData().get(0).getPrice(), response.getData().get(0).getPrice());
                    assertEquals(responseList.getData().get(0).getDescription(), response.getData().get(0).getDescription());
                });

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getProductById() throws Exception{

        BaseResponse<ProductResponseDTO> responseList = new BaseResponse<>(
                true,
                200,
                new ProductResponseDTO(
                        1, "item", "item baru", new BigDecimal(1), new Date()
                )
        );

        when(this.productService.getById(1)).thenReturn((new Product(
                1, "item", "item baru", new BigDecimal(1), new Date()
        )));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect( result -> {
                    BaseResponse<ProductResponseDTO> response = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<ProductResponseDTO>>() {
                    });

                    assertEquals(responseList.getData().getId(), response.getData().getId());
                    assertEquals(responseList.getData().getName(), response.getData().getName());
                    assertEquals(responseList.getData().getPrice(), response.getData().getPrice());
                    assertEquals(responseList.getData().getDescription(), response.getData().getDescription());
                });

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void createProduct() throws Exception{

        BaseResponse<ProductResponseDTO> responseList = new BaseResponse<>(
                true,
                200,
                new ProductResponseDTO(
                        1, "item", "item baru", new BigDecimal(1), new Date()
                )
        );

        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "item", "item baru", new BigDecimal(1)
        );

        when(this.productService.create(productRequestDTO)).thenReturn((new Product(
                1, "item", "item baru", new BigDecimal(1), new Date()
        )));

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(productRequestDTO))
                )
                .andExpect(status().isCreated())
                .andExpect( result -> {
                    BaseResponse<ProductResponseDTO> response = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<ProductResponseDTO>>() {
                    });

                    assertEquals(responseList.getData().getId(), response.getData().getId());
                    assertEquals(responseList.getData().getName(), response.getData().getName());
                    assertEquals(responseList.getData().getPrice(), response.getData().getPrice());
                    assertEquals(responseList.getData().getDescription(), response.getData().getDescription());
                });

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateProduct() throws Exception{

        BaseResponse<ProductResponseDTO> responseList = new BaseResponse<>(
                true,
                200,
                new ProductResponseDTO(
                        1, "item", "item baru", new BigDecimal(1), new Date()
                )
        );

        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "item", "item baru", new BigDecimal(1)
        );

        when(this.productService.update(1, productRequestDTO)).thenReturn((new Product(
                1, "item", "item baru", new BigDecimal(1), new Date()
        )));

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(productRequestDTO))
                )
                .andExpect(status().isOk())
                .andExpect( result -> {
                    BaseResponse<ProductResponseDTO> response = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<ProductResponseDTO>>() {
                    });

                    assertEquals(responseList.getData().getId(), response.getData().getId());
                    assertEquals(responseList.getData().getName(), response.getData().getName());
                    assertEquals(responseList.getData().getPrice(), response.getData().getPrice());
                    assertEquals(responseList.getData().getDescription(), response.getData().getDescription());
                });

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteProduct() throws Exception{

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(this.productService, times(1)).delete(1);
    }


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void searchByNames() throws Exception{

        BaseResponse<List<ProductResponseDTO>> responseList = new BaseResponse<>(
                true,
                200,
                List.of(new ProductResponseDTO(
                        1, "item", "item baru", new BigDecimal(1), new Date()
                ))
        );

        when(this.productService.getByName("item")).thenReturn(List.of(new Product(
                1, "item", "item baru", new BigDecimal(1), new Date()
        )));

        mockMvc.perform(get("/products/names?name=item"))
                .andExpect(status().isOk())
                .andExpect( result -> {
                    BaseResponse<List<ProductResponseDTO>> response = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<List<ProductResponseDTO>>>() {
                    });

                    assertEquals(responseList.getData().get(0).getId(), response.getData().get(0).getId());
                    assertEquals(responseList.getData().get(0).getName(), response.getData().get(0).getName());
                    assertEquals(responseList.getData().get(0).getPrice(), response.getData().get(0).getPrice());
                    assertEquals(responseList.getData().get(0).getDescription(), response.getData().get(0).getDescription());
                });

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void searchByPriceRange() throws Exception{

        BaseResponse<List<ProductResponseDTO>> responseList = new BaseResponse<>(
                true,
                200,
                List.of(new ProductResponseDTO(
                        1, "item", "item baru", new BigDecimal(1), new Date()
                ))
        );

        when(this.productService.getByPriceBetween(any(), any())).thenReturn(List.of(new Product(
                1, "item", "item baru", new BigDecimal(1), new Date()
        )));

        mockMvc.perform(get("/prices-between?min=1&max=2"))
                .andExpect(status().isOk())
                .andExpect( result -> {
                    BaseResponse<List<ProductResponseDTO>> response = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<BaseResponse<List<ProductResponseDTO>>>() {
                    });

                    assertEquals(responseList.getData().get(0).getId(), response.getData().get(0).getId());
                    assertEquals(responseList.getData().get(0).getName(), response.getData().get(0).getName());
                    assertEquals(responseList.getData().get(0).getPrice(), response.getData().get(0).getPrice());
                    assertEquals(responseList.getData().get(0).getDescription(), response.getData().get(0).getDescription());
                });

    }

}



