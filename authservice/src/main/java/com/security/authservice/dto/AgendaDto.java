package com.security.authservice.dto;

import com.security.authservice.entity.Agenda;
import com.security.authservice.entity.SysUser;
import com.security.authservice.enums.StatusAgenda;
import com.security.authservice.enums.TipoAgendamento;
import com.security.authservice.enums.TipoRepeticao;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDto {

    private UUID id;
    private String nome;
    private String descricao;
    private StatusAgenda status;
    private TipoAgendamento tipoAgendamento;
    private TipoRepeticao tipoRepeticao;
    private LocalDate horarioEnvio;
    private Boolean enviarAgora;
    private String whatsapp;
    private SysUser sysUser;

    public static Agenda toEntity(AgendaDto agenda) {
        return Agenda.builder()
                .id(agenda.getId())
                .nome(agenda.getNome())
                .descricao(agenda.getDescricao())
                .status(agenda.getStatus())
                .tipoAgendamento(agenda.getTipoAgendamento())
                .tipoRepeticao(agenda.getTipoRepeticao())
                .horarioEnvio(agenda.getHorarioEnvio())
                .enviarAgora(false)
                .whatssap(agenda.getWhatsapp())
                .sysUser(agenda.getSysUser())
                .build();
    }

    public static AgendaDto toDto(Agenda agenda) {
        return AgendaDto.builder()
                .id(agenda.getId())
                .nome(agenda.getNome())
                .descricao(agenda.getDescricao())
                .status(agenda.getStatus())
                .tipoAgendamento(agenda.getTipoAgendamento())
                .tipoRepeticao(agenda.getTipoRepeticao())
                .horarioEnvio(agenda.getHorarioEnvio())
                .enviarAgora(agenda.isEnviarAgora())
                .whatsapp(agenda.getWhatssap())
                .sysUser(agenda.getSysUser())
                .build();
    }
}
