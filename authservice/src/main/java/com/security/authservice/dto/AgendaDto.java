package com.security.authservice.dto;

import com.security.authservice.entity.Agenda;
import com.security.authservice.enums.StatusAgenda;

import java.time.LocalDate;
import java.util.UUID;

public record AgendaDto(UUID id,
                        String titulo,
                        String descricao,
                        LocalDate data,
                        StatusAgenda statusAgenda) {

    public static Agenda toEntity(AgendaDto agenda) {
        return Agenda
                .builder()
                .id(agenda.id)
                .titulo(agenda.titulo)
                .descricao(agenda.descricao)
                .data(agenda.data)
                .statusAgenda(agenda.statusAgenda)
                .build();

    }

    public static AgendaDto toDto(Agenda agenda) {
        return new AgendaDto(
                agenda.getId(),
                agenda.getTitulo(),
                agenda.getDescricao(),
                agenda.getData(),
                agenda.getStatusAgenda()
        );
    }
}
