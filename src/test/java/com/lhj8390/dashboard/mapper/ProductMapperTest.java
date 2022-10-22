package com.lhj8390.dashboard.mapper;

import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.dto.ProductSaveRequestDTO;
import com.lhj8390.dashboard.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    @DisplayName("toEntity 수행 테스트")
    public void productMapper_toEntity_test() {
        ProductSaveRequestDTO dto = ProductSaveRequestDTO.builder()
                .name("product1")
                .thumnail("thumnail")
                .price(1000)
                .amount(1)
                .category("category")
                .build();

        Product product = productMapper.toEntity(dto);

        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getPrice(), dto.getPrice());
        assertEquals(product.getAmount(), dto.getAmount());
        assertEquals(product.getCategory(), dto.getCategory());
    }

    @Test
    @DisplayName("toDto 수행 테스트")
    public void productMapper_toDto_test() {
        Product product = Product.builder()
                .name("product1")
                .thumnail("thumnail")
                .price(1000)
                .amount(1)
                .category("category")
                .build();

        ProductSaveRequestDTO dto = productMapper.toSaveDto(product);
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getPrice(), dto.getPrice());
        assertEquals(product.getAmount(), dto.getAmount());
        assertEquals(product.getCategory(), dto.getCategory());
    }
}
