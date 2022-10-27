package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.auth.JwtTokenProvider;
import com.lhj8390.dashboard.model.dto.user.AuthResponseDTO;
import com.lhj8390.dashboard.model.dto.user.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        authenticationManager.authenticate(authentication);

        return AuthResponseDTO.builder()
                .token(tokenProvider.generateToken(authentication))
                .build();
    }
}
