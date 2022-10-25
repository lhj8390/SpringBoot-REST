package com.lhj8390.dashboard.service;


import com.lhj8390.dashboard.exception.NoItemException;
import com.lhj8390.dashboard.mapper.ProductMapper;
import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.model.dto.product.ProductResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.product.ProductUpdateRequestDTO;
import com.lhj8390.dashboard.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                .category(ProductCategory.ELECTRONIC.name())
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
        Pageable pageable = PageRequest.of(1, 20, Sort.Direction.DESC, "id");
        doReturn(new PageImpl<Product>(new ArrayList<>(), pageable, pageable.getOffset()))
                .when(productRepository)
                .findAll(pageable);
        productService.getProductList(pageable);

        verify(productRepository).findAll(pageable);
    }

    @Test
    @DisplayName("특정 상품 상세 내용을 찾는다.")
    public void get_product_one() {
        Optional<Product> product = Optional.ofNullable(productMapper.toEntity(productSaveRequestDTO));
        doReturn(product).when(productRepository).findById(anyLong());
        ProductResponseDTO responseDTO = productService.getProduct(anyLong());

        assertThat(responseDTO).isNotNull();
        verify(productRepository).findById(anyLong());
    }

    @Test
    @DisplayName("존재하지 않는 상품을 찾는다.")
    public void get_product_not_exist() {
        doReturn(Optional.empty()).when(productRepository).findById(anyLong());
        NoItemException exception = assertThrows(NoItemException.class, () -> productService.getProduct(anyLong()));

        assertEquals(exception.getMessage(), "해당 item 이 없습니다.");
    }

    @Test
    @DisplayName("상품을 수정한다.")
    public void update_product() {
        Optional<Product> productOptional = Optional.ofNullable(productMapper.toEntity(productSaveRequestDTO));
        doReturn(productOptional).when(productRepository).findById(1L);
        ProductUpdateRequestDTO updateData = ProductUpdateRequestDTO.builder()
                .name("product2")
                .amount(2)
                .price(1000)
                .category(ProductCategory.ELECTRONIC.name())
                .build();
        productOptional.ifPresent(product ->
            product.update(updateData.getName(), updateData.getAmount(), updateData.getPrice(), ProductCategory.BOOK)
        );

        productService.updateProduct(1L, updateData);

        assertThat(productOptional.get()).isNotNull();
        assertEquals(productOptional.get().getName(), updateData.getName());
    }

    @Test
    @DisplayName("존재하지 않는 상품을 수정한다.")
    public void update_product_not_exist() {
        doReturn(Optional.empty()).when(productRepository).findById(1L);
        NoItemException exception = assertThrows(NoItemException.class,
                () -> productService.updateProduct(1L, any(ProductUpdateRequestDTO.class))
        );

        assertEquals(exception.getMessage(), "해당 item 이 없습니다.");
    }

    @Test
    @DisplayName("상품을 삭제한다.")
    public void delete_product() {
        Optional<Product> productOptional = Optional.ofNullable(productMapper.toEntity(productSaveRequestDTO));
        doReturn(productOptional).when(productRepository).findById(1L);

        productOptional.ifPresent(product ->
                doNothing().when(productRepository).delete(product)
        );

        productService.deleteProduct(1L);

        verify(productRepository).delete(any(Product.class));
    }

    @Test
    @DisplayName("존재하지 않는 상품을 삭제한다.")
    public void delete_product_not_exist() {
        doReturn(Optional.empty()).when(productRepository).findById(anyLong());
        NoItemException exception = assertThrows(NoItemException.class, () -> productService.deleteProduct(anyLong()));

        assertEquals(exception.getMessage(), "해당 item 이 없습니다.");
    }
}
