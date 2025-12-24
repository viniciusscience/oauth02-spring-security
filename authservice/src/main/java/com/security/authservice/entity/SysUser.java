package com.security.authservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.security.authservice.enums.StatusAgenda;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SysUser   {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    @JsonIgnoreProperties
    private String password;

    private String email;

    private String descricao;
}
