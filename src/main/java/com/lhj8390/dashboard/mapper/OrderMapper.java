package com.lhj8390.dashboard.mapper;

import com.lhj8390.dashboard.model.Order;
import com.lhj8390.dashboard.model.dto.order.OrderListResponseDTO;
import com.lhj8390.dashboard.model.dto.order.OrderSaveRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderSaveRequestDTO requestDTO);
    OrderSaveRequestDTO toSaveDto(Order order);
    List<OrderListResponseDTO> toListDto(List<Order> orderList);

}