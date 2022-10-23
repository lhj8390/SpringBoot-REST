package com.lhj8390.dashboard.model.dto.order;

import com.lhj8390.dashboard.model.OrderType;
import com.lhj8390.dashboard.model.Product;
import lombok.Builder;

@Builder
public class OrderSaveRequestDTO {
    private Product product;
    private OrderType state;
    private Integer amount;
    private Integer price;
}
