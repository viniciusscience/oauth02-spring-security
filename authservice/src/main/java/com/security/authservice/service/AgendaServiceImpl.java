package com.security.authservice.service;

import com.security.authservice.dto.AgendaDto;
import com.security.authservice.enums.StatusAgenda;
import com.security.authservice.repository.AgendaRepository;
import com.security.authservice.repository.UserRepository;
import com.security.authservice.security.context.ServiceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AgendaDto salvar(AgendaDto dto) {
        final var user = userRepository.findByUsername(ServiceContext.getUser()).orElseThrow();
        dto.setSysUser(user);

        final var agendaEntity = agendaRepository.save(AgendaDto.toEntity(dto));
        return AgendaDto.toDto(agendaEntity);
    }

    @Override
    public AgendaDto buscarPorId(UUID id) {
        final var agendaEntity = agendaRepository.findById(id).orElse(null);

        if (agendaEntity == null) return null;

        return AgendaDto.toDto(agendaEntity);
    }

    @Override
    @Transactional
    public AgendaDto atualizar(UUID id, StatusAgenda statusAgenda) {
        final var agendaEntity = agendaRepository.findById(id).orElse(null);
        if (agendaEntity == null) return null;
        agendaEntity.setStatus(statusAgenda);
        return AgendaDto.toDto(agendaRepository.save(agendaEntity));
    }
}
