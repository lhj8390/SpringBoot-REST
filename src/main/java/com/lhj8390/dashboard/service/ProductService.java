package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.model.dto.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.ProductSaveRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductListResponseDTO> getProductList();

    void insertProduct(ProductSaveRequestDTO dto);
}
