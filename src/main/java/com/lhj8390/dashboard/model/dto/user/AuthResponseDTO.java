package com.lhj8390.dashboard.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AuthResponseDTO {
    private String token;
}
