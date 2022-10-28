package com.lhj8390.dashboard.repository;

import com.lhj8390.dashboard.model.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    private Product product = null;

    @BeforeEach
    public void setup() {
         product = Product.builder()
                 .name("product1")
                 .price(1000)
                 .thumnail("이미지")
                 .amount(4)
                 .build();
    }

    @Test
    @DisplayName("상품을 등록합니다.")
    public void create_product() {

        final Product result = productRepository.save(product);

        assertThat(result).isNotNull();
        assertEquals(result.getName(), "product1");
        assertEquals(result.getPrice(), 1000);
        assertEquals(result.getAmount(), 4);
        assertEquals(result.getThumnail(), "이미지");
    }

    @Test
    @DisplayName("Id로 상품을 찾습니다.")
    public void find_order_by_Id() {

        productRepository.save(product);
        Product result = productRepository.findById(product.getId()).orElse(null);

        assertThat(result).isNotNull();
        assertEquals(result.getName(), "product1");
        assertEquals(result.getPrice(), 1000);
        assertEquals(result.getAmount(), 4);
        assertEquals(result.getThumnail(), "이미지");
    }
}
