package com.security.authservice.dto;

import com.security.authservice.entity.Agenda;
import com.security.authservice.enums.StatusAgenda;
import com.security.authservice.enums.TipoAgendamento;
import com.security.authservice.enums.TipoRepeticao;

import java.time.LocalDate;
import java.util.UUID;

public record AgendaDto(UUID id,
                        String nome,
                        String descricao,
                        StatusAgenda statusAgenda,
                        TipoAgendamento tipoAgendamento,
                        TipoRepeticao tipoRepeticao,
                        LocalDate horarioEnvio,
                        boolean enviarAgora,
                        String whatssap



) {

    public static Agenda toEntity(AgendaDto agenda) {
        return Agenda
                .builder()
                .id(agenda.id)
                .descricao(agenda.descricao)
                .status(agenda.statusAgenda)
                .tipoAgendamento(agenda.tipoAgendamento)
                .tipoRepeticao(agenda.tipoRepeticao)
                .horarioEnvio(agenda.horarioEnvio)
                .enviarAgora(agenda.enviarAgora)
                .whatssap(agenda.whatssap)
                .build();

    }

    public static AgendaDto toDto(Agenda agenda) {
        return new AgendaDto(
                agenda.getId(),
                agenda.getNome(),
                agenda.getDescricao(),
                agenda.getStatus(),
                agenda.getTipoAgendamento(),
                agenda.getTipoRepeticao(),
                agenda.getHorarioEnvio(),
                agenda.isEnviarAgora(),
                agenda.getWhatssap()
        );
    }
}
