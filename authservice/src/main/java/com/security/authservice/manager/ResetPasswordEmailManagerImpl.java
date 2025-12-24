package com.security.authservice.manager;

import com.security.authservice.dto.CreateResetTokenInput;
import com.security.authservice.forgot.password.email.utils.TokenEmailManager;
import com.security.authservice.manager.exception.EmailNotFoundException;
import com.security.authservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class ResetPasswordEmailManagerImpl implements ResetPasswordEmailManager {
    private static final String ASSUNTO = "Recebemos sua solicitacão para redefinir sua senha, para redefinir clique no link abaixo";
    private final EmailService emailService;
    private final TokenEmailManager tokenEmailManager;

    @Override
    @Transactional
    public void enviar(final String email) {
        if (email == null || email.isBlank()) throw new EmailNotFoundException("E-mail inválido para reset de senha");
        final var token = generateToken();
        createResetToken(token, email);
        emailService.enviar(email, ASSUNTO, conteudoHtml(token));
    }

    private String generateToken() {
        return tokenEmailManager.generateEmailToken();
    }

    private void createResetToken(String token, String email) {
        tokenEmailManager.createResetToken(
                CreateResetTokenInput
                 .builder()
                .email(email)
                .token(token)
                .build());
    }

    private String conteudoHtml(String token) {
        return String.format("""
                  <p>  Recebemos sua solicitação para redefinir sua senha, para redefinir clique no link abaixo </p>
                    <a href="http://localhost:8080/reset-password?token=%s">
                        Redefinir senha
                    </a>
                """, token);
    }
}
