package com.security.authservice.query;

import com.security.authservice.dao.AgendaDAO;
import com.security.authservice.dto.AgendaProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAgendasImpl implements GetAgendas {
    private final AgendaDAO agendaDAO;

    @Override
    public List<AgendaProjection> getAgenda() {
        return agendaDAO.getAgenda();
    }
}
