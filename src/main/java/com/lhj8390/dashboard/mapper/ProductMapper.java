package com.lhj8390.dashboard.mapper;

import com.lhj8390.dashboard.model.entity.Product;
import com.lhj8390.dashboard.model.dto.product.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductSaveRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductSaveRequestDTO requestDTO);
    ProductResponseDTO toDto(Product product);
    ProductSaveRequestDTO toSaveDto(Product product);
    List<ProductListResponseDTO> toListDto(List<Product> productList);

}
