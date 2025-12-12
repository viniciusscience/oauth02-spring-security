package com.security.authservice.service;

import com.security.authservice.dto.UserRegisterInput;
import com.security.authservice.entity.SysUser;
import com.security.authservice.repository.UserRepository;
import com.security.authservice.security.exception.UsuarioExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(UserRegisterInput user) {
        final var userExists = userRepository.findByUsername(user.email()).orElse(null);

        if (userExists != null) throw new UsuarioExistenteException("Email ja cadastrado.");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        SysUser sysUser = SysUser.builder()  //
                .descricao(null)  //
                .email(user.email())  //
                .username(user.nome())  //
                .password(passwordEncoder.encode(user.senha()))  //
                .build(); //

        userRepository.save(sysUser);

    }
}
