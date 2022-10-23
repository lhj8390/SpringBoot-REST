package com.lhj8390.dashboard.controller;

import com.lhj8390.dashboard.model.dto.product.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductResponseDTO;
import com.lhj8390.dashboard.model.dto.product.ProductSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.product.ProductUpdateRequestDTO;
import com.lhj8390.dashboard.model.response.ApiResponse;
import com.lhj8390.dashboard.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductListResponseDTO> dtoList = productService.getProductList(pageable);

        return ApiResponse.toResponse(HttpStatus.OK, "", dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ProductResponseDTO responseDTO = productService.getProduct(id);

        return ApiResponse.toResponse(HttpStatus.OK, "", responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody ProductSaveRequestDTO dto) {

        productService.insertProduct(dto);
        return ApiResponse.toResponse(HttpStatus.CREATED, "등록 성공!", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody ProductUpdateRequestDTO dto) {

        productService.updateProduct(id, dto);
        return ApiResponse.toResponse(HttpStatus.NO_CONTENT, "수정 성공!", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        productService.deleteProduct(id);
        return ApiResponse.toResponse(HttpStatus.NO_CONTENT, "삭제 성공!", null);
    }
}
