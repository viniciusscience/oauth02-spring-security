package com.security.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordInput {
    private String token;
    private String senha;
    private String confirmaSenha;
}
