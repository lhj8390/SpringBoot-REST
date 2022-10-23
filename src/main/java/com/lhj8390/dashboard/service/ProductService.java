package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.model.dto.product.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.product.ProductUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<ProductListResponseDTO> getProductList(Pageable pageable);

    void insertProduct(ProductSaveRequestDTO dto);

    ProductResponseDTO getProduct(Long id);

    void updateProduct(Long id, ProductUpdateRequestDTO dto);

    void deleteProduct(Long id);
}
