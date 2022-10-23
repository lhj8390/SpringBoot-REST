package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.mapper.OrderMapper;
import com.lhj8390.dashboard.model.Order;
import com.lhj8390.dashboard.model.dto.order.OrderListResponseDTO;
import com.lhj8390.dashboard.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public List<OrderListResponseDTO> getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.toListDto(orderList);
    }


}
