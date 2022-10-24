package com.lhj8390.dashboard.model.dto.product;

import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.util.EnumPattern;
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

    @NotNull
    @Min(value = 1)
    private Integer amount;

    @NotNull
    @Min(value = 1)
    private Integer price;

    @NotNull
    @EnumPattern(enumClass = ProductCategory.class, name = "category")
    private String category;
}
