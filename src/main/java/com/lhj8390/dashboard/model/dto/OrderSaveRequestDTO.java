package com.lhj8390.dashboard.model.dto;

import com.lhj8390.dashboard.model.OrderType;
import com.lhj8390.dashboard.model.Product;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Builder
public class OrderSaveRequestDTO {
    private Product product;
    private OrderType state;
    private Integer amount;
    private Integer price;
}
