package com.lhj8390.dashboard.controller;


import com.google.gson.Gson;
import com.lhj8390.dashboard.model.OrderType;
import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.model.dto.order.OrderSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.order.OrderUpdateRequestDTO;
import com.lhj8390.dashboard.service.OrderService;
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
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void setup() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void mockMvc_is_not_null() {
        assertThat(orderController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("전체 리스트 조회")
    public void get_order_list() throws Exception {
        final String url = "/api/order/";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("주문 추가")
    public void create_order() throws Exception {
        final String url = "/api/order/";

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
    @DisplayName("주문 등록 시 정보 누락")
    public void create_order_invalid(OrderSaveRequestDTO saveRequestDTO) throws Exception {
        final String url = "/api/order/";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(saveRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("주문 수정")
    public void update_order() throws Exception {
        final String url = "/api/order/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put(url)
                        .content(gson.toJson(updateRequestDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isNoContent());
    }

    @ParameterizedTest
    @MethodSource("invalidUpdateParameter")
    @DisplayName("주문 수정 시 정보 누락")
    public void update_order(OrderUpdateRequestDTO updateRequestDTO) throws Exception {
        final String url = "/api/order/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put(url)
                        .content(gson.toJson(updateRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("주문 삭제")
    public void delete_order() throws Exception {
        final String url = "/api/order/1";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete(url)
        );

        resultActions.andExpect(status().isNoContent());
    }

    private OrderSaveRequestDTO saveRequestDTO() {
        return OrderSaveRequestDTO.builder()
                .state(OrderType.PROCESSING.name())
                .productId(1L)
                .price(1000)
                .amount(1)
                .build();
    }

    private OrderUpdateRequestDTO updateRequestDTO() {
        return OrderUpdateRequestDTO.builder()
                .price(100)
                .amount(1)
                .orderType(OrderType.REJECTED.name())
                .build();
    }

    private static Stream<Arguments> invalidSaveParameter() {
        return Stream.of(
            Arguments.of(new OrderSaveRequestDTO(1L, "invalid", 0, 1)),
            Arguments.of(new OrderSaveRequestDTO(null, OrderType.PROCESSING.name(), 1, 1)),
            Arguments.of(new OrderSaveRequestDTO(1L, OrderType.REJECTED.name(), 1, 0))
        );
    }

    private static Stream<Arguments> invalidUpdateParameter() {
        return Stream.of(
                Arguments.of(new OrderUpdateRequestDTO( 0, 1, OrderType.COMPLETED.name())),
                Arguments.of(new OrderUpdateRequestDTO( 4, 1, "invalid")),
                Arguments.of(new OrderUpdateRequestDTO(2, 0, ProductCategory.ELECTRONIC.name()))
        );
    }
}
