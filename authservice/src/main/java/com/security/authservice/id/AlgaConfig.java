package com.security.authservice.id;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {

    @Bean
    public Notificador notificadorEmail() {
        return new NotificadorEmail("XXX-NotificadorEmail01");
    }
}
