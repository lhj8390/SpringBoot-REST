package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.model.dto.order.OrderListResponseDTO;
import com.lhj8390.dashboard.model.dto.order.OrderSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.order.OrderUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Page<OrderListResponseDTO> getOrderList(Pageable pageable);

    void insertOrder(OrderSaveRequestDTO dto);

    void updateOrder(Long id, OrderUpdateRequestDTO dto);

    void deleteOrder(Long id);

}
