package com.security.authservice.repository;

import com.security.authservice.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SysUser, Long> {

    Optional<User> findByUsername(String username);

    User findByEmail(String email);
}
