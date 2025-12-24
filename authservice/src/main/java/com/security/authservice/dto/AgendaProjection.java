package com.security.authservice.dto;

import com.security.authservice.enums.StatusAgenda;
import lombok.Data;

import java.util.UUID;

@Data
public class AgendaProjection {
    UUID id;
    String nome;
    String descricao;
    StatusAgenda status;
    String nomeUsuario;
}
