package com.security.authservice.entity;

import com.security.authservice.enums.StatusAgenda;
import com.security.authservice.enums.TipoAgendamento;
import com.security.authservice.enums.TipoRepeticao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "agenda")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAgenda status = StatusAgenda.EM_ANDAMENTO;

    @ManyToOne
    @JoinColumn(name = "sys_user", nullable = false)
    private SysUser sysUser;

    @Enumerated(EnumType.STRING)
    private TipoAgendamento tipoAgendamento;

    @Enumerated(EnumType.STRING)
    private TipoRepeticao tipoRepeticao;

    private String whatssap;

    private LocalDate horarioEnvio;

    private boolean enviarAgora;

    public boolean isEnvioAutomatico() {
        return TipoAgendamento.ENVIO_AUTOMATICO.equals(this.tipoAgendamento);
    }

    public boolean isEnviarAgora() {
        return Boolean.TRUE.equals(enviarAgora);
    }
}
