package com.lhj8390.dashboard.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductListResponseDTO {
    private Long id;
    private String thumnail;
    private String category;
    private Integer amount;
    private Integer price;
}
