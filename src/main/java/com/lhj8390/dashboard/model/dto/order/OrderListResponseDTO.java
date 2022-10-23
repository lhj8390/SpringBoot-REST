package com.lhj8390.dashboard.model.dto.order;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponseDTO {
    private Long OrderId;
    private String productName;
    private Date orderDt;
    private String state;
    private Integer amount;
    private Integer price;
}
