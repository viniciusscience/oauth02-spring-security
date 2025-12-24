package com.security.authservice.service;

public interface EmailService {
    void enviar(String para, String assunto, String html);
}
