package com.security.authservice.dto;

import com.security.authservice.enums.StatusAgenda;
import lombok.Data;

@Data
public class AgendaProjection {
    String nome;
    String descricao;
    StatusAgenda status;
    String nomeUsuario;
}
