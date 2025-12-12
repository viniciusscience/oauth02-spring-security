package com.security.authservice.dto;

import com.security.authservice.entity.SysUser;

import java.util.UUID;

public record UserDto (
         UUID id,
         String username,
         String password,
         String email,
         String descricao) {

    public static UserDto toDto(SysUser sysUser) {
        return new UserDto (
                sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getEmail(),
                sysUser.getDescricao()

        );
    }
}

