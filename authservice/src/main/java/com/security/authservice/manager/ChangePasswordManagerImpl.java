package com.security.authservice.manager;

import com.security.authservice.dto.ChangePasswordInput;
import com.security.authservice.dto.PasswordResetTokenDto;
import com.security.authservice.entity.SysUser;
import com.security.authservice.manager.exception.SenhasNaoCoincidemException;
import com.security.authservice.manager.exception.TokenException;
import com.security.authservice.service.PasswordResetTokenService;
import com.security.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangePasswordManagerImpl implements ChangePasswordManager {

    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserService userService;

    @Override
    @Transactional
    public void alterar(ChangePasswordInput changePasswordInput) {
        validarSenhas(changePasswordInput);
        final var token = findTokenEmail(changePasswordInput);
        final var usuario = findUserFromToken(token);
        final var novaSenha = passwordEncoder.encode(changePasswordInput.getSenha());

        usuario.setPassword(novaSenha);
    }

    private void validarSenhas(ChangePasswordInput input) {
        final var senhasNaoCoincidem = !input.getSenha().equals(input.getConfirmaSenha());
        if (senhasNaoCoincidem) throw new SenhasNaoCoincidemException("As senhas não coincidem");
    }

    private SysUser findUserFromToken(PasswordResetTokenDto token) {
        return userService.findByEmail(token.getEmail());
    }

    private PasswordResetTokenDto findTokenEmail(ChangePasswordInput changePasswordInput) {
        return passwordResetTokenService
                .findByToken(changePasswordInput.getToken())
                .orElseThrow(() -> new TokenException("Token não encontrado."));
    }
}
