package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.mapper.ProductMapper;
import com.lhj8390.dashboard.model.Product;
import com.lhj8390.dashboard.model.dto.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.ProductSaveRequestDTO;
import com.lhj8390.dashboard.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductListResponseDTO> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productMapper.toListDto(productList);
    }

    @Override
    public void insertProduct(ProductSaveRequestDTO dto) {
        productRepository.save(productMapper.toEntity(dto));
    }
}
