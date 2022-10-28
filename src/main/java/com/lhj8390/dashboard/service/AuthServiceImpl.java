package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.auth.JwtTokenProvider;
import com.lhj8390.dashboard.exception.NoItemException;
import com.lhj8390.dashboard.mapper.UserMapper;
import com.lhj8390.dashboard.model.RoleType;
import com.lhj8390.dashboard.model.dto.user.AuthResponseDTO;
import com.lhj8390.dashboard.model.dto.user.JoinRequestDTO;
import com.lhj8390.dashboard.model.dto.user.LoginRequestDTO;
import com.lhj8390.dashboard.model.entity.Role;
import com.lhj8390.dashboard.model.entity.User;
import com.lhj8390.dashboard.repository.RoleRepository;
import com.lhj8390.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        authenticationManager.authenticate(authentication);

        return AuthResponseDTO.builder()
                .token(tokenProvider.generateToken(authentication))
                .build();
    }

    @Override
    public void join(JoinRequestDTO dto) {

        Role role = roleRepository.findByName(RoleType.valueOf(dto.getRole()))
                .orElseThrow(() -> new NoItemException("권한 설정이 잘못되었습니다."));

        String password = passwordEncoder.encode(dto.getPassword());

        User user = userMapper.toEntity(dto);
        user.setPassword(password);
        user.setRoles(role);

        userRepository.save(user);
    }
}
