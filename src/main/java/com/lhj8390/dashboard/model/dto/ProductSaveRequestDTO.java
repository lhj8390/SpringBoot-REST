package com.lhj8390.dashboard.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ProductSaveRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String thumnail;
    @Min(value = 1)
    private Integer amount;
    @Min(value = 1)
    private Integer price;
    @NotBlank
    private String category;
}
