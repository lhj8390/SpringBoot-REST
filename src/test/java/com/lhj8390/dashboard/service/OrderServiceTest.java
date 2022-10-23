package com.lhj8390.dashboard.service;


import com.lhj8390.dashboard.model.Order;
import com.lhj8390.dashboard.model.OrderType;
import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.model.dto.order.OrderListResponseDTO;
import com.lhj8390.dashboard.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    public void setup() {
        order = Order.builder()
                .product(new Product(1L, "test", "pic", 1, 1000, ProductCategory.ELECTRONIC))
                .orderDt(new Date())
                .amount(1)
                .price(1000)
                .state(OrderType.COMPLETED)
                .build();
    }

    @Test
    @DisplayName("주문을 추가한다.")
    public void create_order() {
        doNothing().when(orderRepository).save(order);

    }

    @Test
    public void get_order_all() {
        doReturn(new ArrayList<>())
                .when(orderRepository)
                .findAll();
        List<OrderListResponseDTO> orderList = orderService.getOrderList();

        assertThat(orderList).isEmpty();
    }
}
