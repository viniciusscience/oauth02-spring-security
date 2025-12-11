package com.security.authservice.controller.agenda;

import com.security.authservice.dto.AgendaDto;
import com.security.authservice.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agendamento")
@RequiredArgsConstructor
public class CriarAgendamentoController {

    private final AgendaService agendaService;

    @PostMapping
    public ResponseEntity<AgendaDto> criarAgendamento(@RequestBody AgendaDto agendaDto) {
       final AgendaDto agenda = agendaService.salvar(agendaDto);
        return ResponseEntity.ok(agenda);
    }
}
