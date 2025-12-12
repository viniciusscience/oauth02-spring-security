package com.security.authservice.controller.agenda;

import com.security.authservice.dto.AgendaProjection;
import com.security.authservice.query.GetAgendas;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendaDAOController {

    private final GetAgendas getAgendas;

    @GetMapping
    public List<AgendaProjection> getAgendas() {
        return getAgendas.getAgenda();
    }
}
