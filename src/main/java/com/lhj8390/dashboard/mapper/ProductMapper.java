package com.lhj8390.dashboard.mapper;

import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.dto.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.ProductSaveRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductSaveRequestDTO requestDTO);
    ProductSaveRequestDTO toSaveDto(Product product);
    List<ProductListResponseDTO> toListDto(List<Product> productList);

}
