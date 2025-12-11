package com.security.authservice.query;

import com.security.authservice.dto.AgendaProjection;

import java.util.List;

public interface GetAgendas {

    List<AgendaProjection> getAgenda();
}
