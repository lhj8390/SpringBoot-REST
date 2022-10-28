package com.lhj8390.dashboard.model.dto.user;

import com.lhj8390.dashboard.model.RoleType;
import com.lhj8390.dashboard.util.EnumPattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JoinRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email;

    @NotBlank
    @Size(min = 8, message = "비밀번호는 8자 이상입니다.")
    private String password;

    @NotNull
    @EnumPattern(enumClass = RoleType.class, name = "RoleType")
    private String role;
}
