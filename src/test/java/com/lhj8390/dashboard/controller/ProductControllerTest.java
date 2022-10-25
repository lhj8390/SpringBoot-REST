package com.lhj8390.dashboard.controller;


import com.google.gson.Gson;
import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.model.dto.product.ProductSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.product.ProductUpdateRequestDTO;
import com.lhj8390.dashboard.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void setup() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void mockMvc_is_not_null() {
        assertThat(productController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("전체 리스트 조회")
    public void get_product_list() throws Exception {
        final String url = "/api/product/";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 상품 조회")
    public void get_product_one() throws Exception {
        final String url = "/api/product/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 등록")
    public void create_product() throws Exception {
        final String url = "/api/product/";

        System.out.println(gson.toJson(saveRequestDTO()));

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(saveRequestDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource("invalidSaveParameter")
    @DisplayName("상품 등록 시 정보 누락")
    public void create_product_invalid(ProductSaveRequestDTO saveRequestDTO) throws Exception {
        final String url = "/api/product/";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(saveRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("특정 상품 수정")
    public void update_product() throws Exception {
        final String url = "/api/product/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put(url)
                        .content(gson.toJson(updateRequestDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isNoContent());
    }

    @ParameterizedTest
    @MethodSource("invalidUpdateParameter")
    @DisplayName("특정 상품 수정 시 정보 누락")
    public void update_product(ProductUpdateRequestDTO updateRequestDTO) throws Exception {
        final String url = "/api/product/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put(url)
                        .content(gson.toJson(updateRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("특정 상품 삭제")
    public void delete_product() throws Exception {
        final String url = "/api/product/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete(url)
        );

        resultActions.andExpect(status().isNoContent());
    }

    private ProductSaveRequestDTO saveRequestDTO() {
        return ProductSaveRequestDTO.builder()
                .name("product")
                .price(1999)
                .amount(1)
                .thumnail("thum")
                .category(ProductCategory.ELECTRONIC.name())
                .build();
    }

    private ProductUpdateRequestDTO updateRequestDTO() {
        return ProductUpdateRequestDTO.builder()
                .name("product")
                .price(1999)
                .amount(1)
                .category(ProductCategory.ELECTRONIC.name())
                .build();
    }

    private static Stream<Arguments> invalidSaveParameter() {
        return Stream.of(
            Arguments.of(new ProductSaveRequestDTO("name", "thum", 0, 1, ProductCategory.ELECTRONIC.name())),
            Arguments.of(new ProductSaveRequestDTO("", "thum", 1, 1, ProductCategory.ELECTRONIC.name())),
            Arguments.of(new ProductSaveRequestDTO("", "thum", 0, 0, null))
        );
    }

    private static Stream<Arguments> invalidUpdateParameter() {
        return Stream.of(
                Arguments.of(new ProductUpdateRequestDTO("name", 0, 1, ProductCategory.ELECTRONIC.name())),
                Arguments.of(new ProductUpdateRequestDTO("name", 4, 1, null)),
                Arguments.of(new ProductUpdateRequestDTO("", 2, 1, ProductCategory.ELECTRONIC.name()))
        );
    }
}
