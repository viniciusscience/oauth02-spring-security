package com.security.authservice.service;

import com.security.authservice.dto.PasswordResetTokenDto;
import com.security.authservice.entity.PasswordResetToken;
import com.security.authservice.repository.PasswordResetTokenRepository;
import com.security.authservice.security.context.ServiceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Optional<PasswordResetTokenDto> findByToken(String token) {
        final Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(token);
        final Optional<PasswordResetTokenDto> passwordResetTokenDto = passwordResetToken.map(PasswordResetTokenDto::new);
        return passwordResetTokenDto;
    }

    @Override
    @Transactional
    public Optional<PasswordResetTokenDto> create(PasswordResetTokenDto passwordResetTokenDto) {

        final var passwordResetToken = passwordResetTokenRepository.save(new PasswordResetToken(passwordResetTokenDto));
        final var passwordResetTokenDt = new PasswordResetTokenDto(passwordResetToken);
        return Optional.of(passwordResetTokenDt);
    }
}
