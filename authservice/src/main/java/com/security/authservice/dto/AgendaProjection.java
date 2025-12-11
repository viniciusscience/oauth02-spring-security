package com.security.authservice.dto;

import lombok.Data;

@Data
public class AgendaProjection {
    String nome;
    String descricao;
    String status;
    String nomeUsuario;
}
