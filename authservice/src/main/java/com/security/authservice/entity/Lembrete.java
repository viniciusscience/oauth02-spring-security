package com.security.authservice.entity;

import com.security.authservice.enums.StatusLembrete;
import com.security.authservice.enums.TipoLembrete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lembrete")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoLembrete tipo;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusLembrete status = StatusLembrete.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "agenda", nullable = false)
    private Agenda agenda;
}
