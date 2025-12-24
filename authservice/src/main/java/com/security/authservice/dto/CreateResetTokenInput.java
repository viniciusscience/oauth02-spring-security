package com.security.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateResetTokenInput {
    String email;
    String token;
}
