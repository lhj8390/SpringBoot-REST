package com.lhj8390.dashboard.service;


import com.lhj8390.dashboard.exception.NoItemException;
import com.lhj8390.dashboard.mapper.OrderMapper;
import com.lhj8390.dashboard.model.entity.Order;
import com.lhj8390.dashboard.model.OrderType;
import com.lhj8390.dashboard.model.entity.Product;
import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.model.dto.order.OrderSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.order.OrderUpdateRequestDTO;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    private OrderSaveRequestDTO saveRequestDTO;

    @BeforeEach
    public void setup() {
        saveRequestDTO = OrderSaveRequestDTO.builder()
                .amount(2)
                .price(2000)
                .state(OrderType.WAIT.name())
                .productId(1L)
                .build();
    }

    @Test
    @DisplayName("주문을 추가한다.")
    public void create_order() {
        Product product = new Product(1L, "test", "thum", 1, 1000, ProductCategory.ELECTRONIC);
        doReturn(Optional.of(product)).when(productRepository).findById(saveRequestDTO.getProductId());
        doReturn(orderMapper.toEntity(saveRequestDTO)).when(orderRepository).save(any(Order.class));
        orderService.insertOrder(saveRequestDTO);

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    @DisplayName("상품이 없는 주문을 추가한다.")
    public void create_order_invalid() {
        doReturn(Optional.empty()).when(productRepository).findById(1L);
        NoItemException exception = assertThrows(NoItemException.class, () -> orderService.insertOrder(saveRequestDTO));

        assertEquals(exception.getMessage(), "해당 상품이 없습니다.");
    }

    @Test
    @DisplayName("주문 리스트를 찾는다.")
    public void get_order_all() {
        Pageable pageable = PageRequest.of(1, 20, Sort.Direction.DESC, "id");
        doReturn(new PageImpl<Order>(new ArrayList<>(), pageable, pageable.getOffset()))
                .when(orderRepository)
                .findAll(pageable);
        orderService.getOrderList(pageable);

        verify(orderRepository).findAll(pageable);
    }

    @Test
    @DisplayName("주문 리스트를 수정한다.")
    @Transactional
    public void update_order() {
        doReturn(Optional.of(orderMapper.toEntity(saveRequestDTO))).when(orderRepository).findById(1L);
        Order order = orderMapper.toEntity(saveRequestDTO);
        OrderUpdateRequestDTO updateRequestDTO = OrderUpdateRequestDTO.builder()
                .orderType(OrderType.PROCESSING.name())
                .amount(2)
                .price(2000)
                .build();
        order.update(OrderType.PROCESSING.name(), 2, 2000);
        orderService.updateOrder(1L, updateRequestDTO);

        assertEquals(orderRepository.findById(1L).get().getState(), order.getState());
        assertEquals(orderRepository.findById(1L).get().getAmount(), order.getAmount());
        assertEquals(orderRepository.findById(1L).get().getPrice(), order.getPrice());
    }

    @Test
    @DisplayName("유효하지 않은 주문을 수정한다.")
    public void update_order_invalid() {
        doReturn(Optional.empty()).when(orderRepository).findById(1L);
        OrderUpdateRequestDTO updateRequestDTO = OrderUpdateRequestDTO.builder()
                .orderType(OrderType.PROCESSING.name())
                .amount(2)
                .price(2000)
                .build();
        NoItemException exception = assertThrows(NoItemException.class, () -> orderService.updateOrder(1L, updateRequestDTO));

        assertEquals(exception.getMessage(), "해당 주문이 없습니다.");
    }

    @Test
    @DisplayName("주문을 삭제한다.")
    public void delete_order() {
        Order order = Order.builder()
                .id(1L)
                .product(Product.builder().id(1L).build())
                .amount(1)
                .price(1000)
                .orderDt(new Date())
                .build();
        doReturn(Optional.of(order)).when(orderRepository).findById(anyLong());
        doNothing().when(orderRepository).delete(order);
        orderService.deleteOrder(1L);

        verify(orderRepository).delete(order);
    }

    @Test
    @DisplayName("유효하지 않은 주문을 삭제한다.")
    public void delete_order_invalid() {
        doReturn(Optional.empty()).when(orderRepository).findById(1L);

        NoItemException exception = assertThrows(NoItemException.class, () -> orderService.deleteOrder(1L));
        assertEquals(exception.getMessage(), "해당 주문이 없습니다.");
    }
}
