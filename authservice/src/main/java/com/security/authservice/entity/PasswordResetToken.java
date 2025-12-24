package com.security.authservice.entity;

import com.security.authservice.dto.PasswordResetTokenDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "password_reset_token",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_password_reset_token_token", columnNames = "token")
        },
        indexes = {
                @Index(name = "idx_password_reset_token_email", columnList = "email"),
                @Index(name = "idx_password_reset_token_expires_at", columnList = "expires_at")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean used;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void markAsUsed() {
        this.used = true;
    }

    public PasswordResetToken(PasswordResetTokenDto dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.token = dto.getToken();
        this.expiresAt = dto.getExpiresAt();
        this.used = dto.isUsed();
        this.createdAt = dto.getCreatedAt();
    }
}
