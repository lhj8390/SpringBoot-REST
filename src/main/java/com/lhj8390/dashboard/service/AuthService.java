package com.lhj8390.dashboard.service;

import com.lhj8390.dashboard.model.dto.user.LoginRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String login(LoginRequestDTO dto);
}
