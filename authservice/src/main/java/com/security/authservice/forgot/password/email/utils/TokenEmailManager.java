package com.security.authservice.forgot.password.email.utils;

import com.security.authservice.dto.CreateResetTokenInput;
import com.security.authservice.dto.PasswordResetTokenDto;

public interface TokenEmailManager {
    void createResetToken(CreateResetTokenInput createResetTokenInput);
    String generateEmailToken();
    void markTokenAsUsed(String token);
}
