package com.security.authservice.dto;

import com.security.authservice.enums.StatusAgenda;
import lombok.Data;

@Data
public class AlterarStatusAgendaInput {
    private StatusAgenda status;
}
