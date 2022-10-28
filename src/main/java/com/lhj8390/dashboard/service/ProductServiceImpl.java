package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.exception.NoItemException;
import com.lhj8390.dashboard.mapper.ProductMapper;
import com.lhj8390.dashboard.model.entity.Product;
import com.lhj8390.dashboard.model.ProductCategory;
import com.lhj8390.dashboard.model.dto.product.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.product.ProductUpdateRequestDTO;
import com.lhj8390.dashboard.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public Page<ProductListResponseDTO> getProductList(Pageable pageable) {
        Page<Product> productList = productRepository.findAll(pageable);
        return new PageImpl<>(
                productMapper.toListDto(productList.getContent()), pageable, productList.getTotalElements()
        );
    }

    @Override
    public void insertProduct(ProductSaveRequestDTO dto) {
        productRepository.save(productMapper.toEntity(dto));
    }

    @Override
    public ProductResponseDTO getProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoItemException("해당 item 이 없습니다."));
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long id, ProductUpdateRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoItemException("해당 item 이 없습니다."));

        product.update(dto.getName(),
                        dto.getAmount(),
                        dto.getPrice(),
                        ProductCategory.ELECTRONIC);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoItemException("해당 item 이 없습니다."));
        productRepository.delete(product);
    }


}
