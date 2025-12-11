package com.security.authservice.actions;

import com.security.authservice.dto.AgendaDto;
import com.security.authservice.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarAgendamentoImpl implements CriarAgendamento {

    private final AgendaService agendaService;

    @Transactional
    @Override
    public void criar(AgendaDto agendaDto) {
        agendaService.salvar(agendaDto);
    }
}
