package com.security.authservice.repository;

import com.security.authservice.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<SysUser, UUID> {

    Optional<SysUser> findByUsername(String username);

    SysUser findByEmail(String email);
}
