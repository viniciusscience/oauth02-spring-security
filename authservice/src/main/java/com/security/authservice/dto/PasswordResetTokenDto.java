package com.security.authservice.dto;

import com.security.authservice.entity.PasswordResetToken;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class PasswordResetTokenDto {

    private UUID id;
    private String email;
    private String token;
    private Instant expiresAt;
    private boolean used;
    private Instant createdAt;

    /**
     * Construtor a partir da entidade
     */
    public PasswordResetTokenDto(PasswordResetToken entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.token = entity.getToken();
        this.expiresAt = entity.getExpiresAt();
        this.used = entity.isUsed();
        this.createdAt = entity.getCreatedAt();
    }

    /**
     * Construtor COMPLETO (bate com o erro do compilador)
     */
    public PasswordResetTokenDto(
            UUID id,
            String email,
            String token,
            Instant expiresAt,
            boolean used,
            Instant createdAt
    ) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expiresAt = expiresAt;
        this.used = used;
        this.createdAt = createdAt;
    }
}
