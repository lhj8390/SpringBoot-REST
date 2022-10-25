package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.auth.JwtTokenProvider;
import com.lhj8390.dashboard.model.dto.user.LoginRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;

    @Override
    public String login(LoginRequestDTO dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), password);
        authenticationManager.authenticate(authentication);

        return tokenProvider.generateToken(authentication);
    }
}
