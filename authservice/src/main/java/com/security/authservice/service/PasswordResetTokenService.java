package com.security.authservice.service;

import com.security.authservice.dto.PasswordResetTokenDto;
import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenService {
    Optional<PasswordResetTokenDto> findByToken(String token);
    Optional<PasswordResetTokenDto> create(PasswordResetTokenDto passwordResetTokenDto);
}
