package com.lhj8390.dashboard.controller;

import com.lhj8390.dashboard.model.dto.order.OrderListResponseDTO;
import com.lhj8390.dashboard.model.dto.order.OrderSaveRequestDTO;
import com.lhj8390.dashboard.model.dto.order.OrderUpdateRequestDTO;
import com.lhj8390.dashboard.model.response.ApiResponse;
import com.lhj8390.dashboard.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "주문 리스트 조회", notes = "모든 주문을 조회합니다.", response = OrderListResponseDTO.class)
    @GetMapping("/")
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderListResponseDTO> dtoList = orderService.getOrderList(pageable);

        return ApiResponse.toResponse(HttpStatus.OK, "", dtoList);
    }

    @ApiOperation(value = "주문 등록", notes = "주문을 등록합니다.")
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody OrderSaveRequestDTO dto) {

        orderService.insertOrder(dto);
        return ApiResponse.toResponse(HttpStatus.CREATED, "등록 성공!", null);
    }

    @ApiOperation(value = "주문 수정", notes = "주문 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody OrderUpdateRequestDTO dto) {

        orderService.updateOrder(id, dto);
        return ApiResponse.toResponse(HttpStatus.NO_CONTENT, "수정 성공!", null);
    }

    @ApiOperation(value = "주문 삭제", notes = "주문을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        orderService.deleteOrder(id);
        return ApiResponse.toResponse(HttpStatus.NO_CONTENT, "삭제 성공!", null);
    }
}
