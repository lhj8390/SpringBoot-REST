package com.lhj8390.dashboard.model.response;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApiResponse {
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime timestamp;
    private int statusCode;
    private String status;
    private String message;
    private Object data;

    public static ResponseEntity<Object> toResponse(HttpStatus status, String message, Object data) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(status.value())
                .status(status.name())
                .message(message)
                .data(data)
                .build(), status);
    }

}
