package com.lhj8390.dashboard.model.dto.product;

import com.lhj8390.dashboard.model.ProductCategory;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private ProductCategory category;
}
