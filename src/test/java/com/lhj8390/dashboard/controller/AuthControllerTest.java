package com.lhj8390.dashboard.controller;

import com.google.gson.Gson;
import com.lhj8390.dashboard.model.dto.user.LoginRequestDTO;
import com.lhj8390.dashboard.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void setup() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void mockMvc_is_not_null() {
        assertThat(authController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("로그인 성공")
    public void login_success() throws Exception {
        final String url = "/api/auth/login";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(loginRequestDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패")
    public void login_fail() throws Exception {
        final String url = "/api/auth/login";

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(invalidLoginRequestDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions.andExpect(status().isBadRequest());
    }

    private LoginRequestDTO loginRequestDTO() {
        return LoginRequestDTO.builder()
                .email("test@test.com")
                .password("test1234")
                .build();
    }

    private LoginRequestDTO invalidLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email("")
                .password("")
                .build();
    }
}
