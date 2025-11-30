package com.security.authservice.id;

public class NotificadorEmail implements Notificador {

    private String hostServidorSmtp;
    private boolean caixaAlta;

    public NotificadorEmail(String hostServidorSmtp) {
        System.out.println("Iniciando notificadorEmail " +  hostServidorSmtp);
        this.hostServidorSmtp = hostServidorSmtp;
    }

    @Override
    public void notificador(String mensagem) {

    }
}
