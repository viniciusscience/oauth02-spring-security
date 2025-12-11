package com.security.authservice.dao;

import com.security.authservice.dto.AgendaProjection;

import java.util.List;

public interface AgendaDAO {
    List<AgendaProjection> getAgenda();
}
