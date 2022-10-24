package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.exception.NoItemException;
import com.lhj8390.dashboard.mapper.OrderMapper;
import com.lhj8390.dashboard.model.Order;
import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.dto.order.OrderListResponseDTO;
import com.lhj8390.dashboard.model.dto.order.OrderSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.order.OrderUpdateRequestDTO;
import com.lhj8390.dashboard.repository.OrderRepository;
import com.lhj8390.dashboard.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public Page<OrderListResponseDTO> getOrderList(Pageable pageable) {
        Page<Order> orderList = orderRepository.findAll(pageable);
        return new PageImpl<>(
                orderMapper.toListDto(orderList.getContent()), pageable, orderList.getTotalElements()
        );
    }

    @Override
    public void insertOrder(OrderSaveRequestDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NoItemException("해당 상품이 없습니다."));
        Order order = orderMapper.toEntity(dto);
        order.setProduct(product);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrder(Long id, OrderUpdateRequestDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoItemException("해당 주문이 없습니다."));
        order.update(dto.getOrderType(), dto.getAmount(), dto.getPrice());
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoItemException("해당 주문이 없습니다."));
        orderRepository.delete(order);
    }


}
