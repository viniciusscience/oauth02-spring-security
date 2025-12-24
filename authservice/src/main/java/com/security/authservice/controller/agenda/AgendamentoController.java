package com.security.authservice.controller.agenda;

import com.security.authservice.dto.AgendaDto;
import com.security.authservice.dto.AlterarStatusAgendaInput;
import com.security.authservice.enums.StatusAgenda;
import com.security.authservice.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendaService agendaService;

    @PostMapping
    public ResponseEntity<AgendaDto> criarAgendamento(@RequestBody AgendaDto agendaDto) {
       final AgendaDto agenda = agendaService.salvar(agendaDto);
        return ResponseEntity.ok(agenda);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AgendaDto> atualizarStatusAgendamento(
            @PathVariable UUID id,
            @RequestBody AlterarStatusAgendaInput alterarStatusAgendaInput) {
        final AgendaDto agenda = agendaService.atualizar(id, alterarStatusAgendaInput.getStatus());
        return ResponseEntity.ok(agenda);
    }
}
