package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.model.dto.OrderListResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderListResponseDTO> getOrderList();

}
