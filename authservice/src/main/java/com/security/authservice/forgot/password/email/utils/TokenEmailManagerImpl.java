package com.security.authservice.forgot.password.email.utils;

import com.security.authservice.dto.CreateResetTokenInput;
import com.security.authservice.dto.PasswordResetTokenDto;
import com.security.authservice.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenEmailManagerImpl implements TokenEmailManager {
    private static final Duration TOKEN_EXPIRATION = Duration.ofMinutes(15);
    private final PasswordEncoder encoder;
    private final PasswordResetTokenService passwordResetTokenService;

    @Override
    @Transactional
    public void createResetToken(CreateResetTokenInput createResetTokenInput) {
        final var passwordResetTokenDto = buildPasswordResetTokenDto(createResetTokenInput);
        passwordResetTokenService.create(passwordResetTokenDto);
    }

    @Override
    public String generateEmailToken() {
        return encoder.encode(UUID.randomUUID().toString());
    }

    @Override
    @Transactional
    public void markTokenAsUsed(String token) {
        final var passwordResetToken = passwordResetTokenService.findByToken(token).orElseThrow();
        passwordResetToken.setUsed(true);
        passwordResetTokenService.create(passwordResetToken);
    }

    private PasswordResetTokenDto buildPasswordResetTokenDto(CreateResetTokenInput createResetTokenInput) {
        Instant now = Instant.now();
        return PasswordResetTokenDto
                .builder()
                .token(createResetTokenInput.getToken())
                .email(createResetTokenInput.getEmail())
                .used(false)
                .createdAt(now)
                .expiresAt(now.plus(TOKEN_EXPIRATION))
                .build();
    }
}
