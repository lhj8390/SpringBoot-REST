package com.lhj8390.dashboard.model.dto.product;

import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.util.EnumPattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductUpdateRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    @Min(value = 1)
    private Integer amount;

    @Min(value = 1)
    @NotNull
    private Integer price;

    @NotNull
    @EnumPattern(enumClass = ProductCategory.class, name = "category")
    private String category;
}
