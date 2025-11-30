package com.security.authservice.service;

import com.security.authservice.dto.AgendaDto;
import com.security.authservice.enums.StatusAgenda;

import java.util.UUID;

public interface AgendaService {

    AgendaDto salvar(AgendaDto dto);
    AgendaDto buscarPorId(UUID id);
    AgendaDto atualizar(UUID id, StatusAgenda statusAgenda);
}
