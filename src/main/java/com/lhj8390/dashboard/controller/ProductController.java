package com.lhj8390.dashboard.controller;

import com.lhj8390.dashboard.model.dto.ProductListResponseDTO;
import com.lhj8390.dashboard.model.dto.ProductSaveRequestDTO;
import com.lhj8390.dashboard.model.response.ApiResponse;
import com.lhj8390.dashboard.service.ProductService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<ProductListResponseDTO> dtoList = productService.getProductList();

        return ApiResponse.toResponse(HttpStatus.OK, "", dtoList);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody ProductSaveRequestDTO dto) {

        productService.insertProduct(dto);
        return ApiResponse.toResponse(HttpStatus.CREATED, "등록 성공!", null);
    }
}
