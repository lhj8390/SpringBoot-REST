package com.lhj8390.dashboard.controller;

import com.lhj8390.dashboard.model.dto.user.LoginRequestDTO;
import com.lhj8390.dashboard.model.response.ApiResponse;
import com.lhj8390.dashboard.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto) {

        String token = authService.login(dto);

        return ApiResponse.toResponse(HttpStatus.OK, "로그인 성공!", token);
    }
}
