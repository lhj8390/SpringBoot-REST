package com.lhj8390.dashboard.service;


import com.lhj8390.dashboard.mapper.UserMapper;
import com.lhj8390.dashboard.model.RoleType;
import com.lhj8390.dashboard.model.dto.user.JoinRequestDTO;
import com.lhj8390.dashboard.model.entity.Role;
import com.lhj8390.dashboard.model.entity.User;
import com.lhj8390.dashboard.repository.RoleRepository;
import com.lhj8390.dashboard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private JoinRequestDTO joinRequestDTO;

    @BeforeEach
    public void setup() {
        joinRequestDTO = JoinRequestDTO.builder()
                .username("user1")
                .email("test@test.com")
                .password("!!!!rtestset")
                .role("ROLE_ADMIN")
                .build();
    }

    @Test
    @DisplayName("회원가입한다.")
    public void join() {
        doReturn(Optional.of(new Role(1, RoleType.ROLE_ADMIN)))
                .when(roleRepository).findByName(any(RoleType.class));
        doReturn(userMapper.toEntity(joinRequestDTO)).when(userRepository).save(any(User.class));
        authService.join(joinRequestDTO);

        verify(userRepository).save(any(User.class));
    }


}
