package com.lhj8390.dashboard.service;


import com.lhj8390.dashboard.mapper.ProductMapper;
import com.lhj8390.dashboard.model.Order;
import com.lhj8390.dashboard.model.OrderType;
import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.dto.OrderListResponseDTO;
import com.lhj8390.dashboard.model.dto.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.ProductSaveRequestDTO;
import com.lhj8390.dashboard.repository.OrderRepository;
import com.lhj8390.dashboard.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private ProductSaveRequestDTO productSaveRequestDTO;

    @BeforeEach
    public void setup() {
        productSaveRequestDTO = ProductSaveRequestDTO.builder()
                .name("product1")
                .thumnail("thumnail")
                .price(1000)
                .amount(1)
                .category("category")
                .build();
    }

    @Test
    @DisplayName("상품을 등록한다.")
    public void create_product() {
        doReturn(productMapper.toEntity(productSaveRequestDTO)).when(productRepository).save(any(Product.class));
        productService.insertProduct(productSaveRequestDTO);

        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("상품 리스트를 찾는다.")
    public void get_product_list() {
        doReturn(new ArrayList<>())
                .when(productRepository)
                .findAll();
        productService.getProductList();

        verify(productRepository).findAll();
    }
}
