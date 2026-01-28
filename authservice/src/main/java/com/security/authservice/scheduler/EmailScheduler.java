package com.security.authservice.scheduler;

import com.security.authservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private final EmailService emailService;

   // @Scheduled(fixedRate = 60000) // 1 minuto
    public void enviarEmailTeste() {
        emailService.enviar(
                "daftwarz@gmail.com",
                "Resetar sua senha",
                "<h2>Funcionou 🚀</h2><p>Email enviado a cada 1 minuto.</p>"
        );
    }
}
