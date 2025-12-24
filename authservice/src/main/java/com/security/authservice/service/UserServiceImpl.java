package com.security.authservice.service;

import com.security.authservice.dto.UserRegisterInput;
import com.security.authservice.entity.SysUser;
import com.security.authservice.repository.UserRepository;
import com.security.authservice.security.exception.UsuarioExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(UserRegisterInput user) {
        final var userExists = findByEmail(user);
        validateUserExists(userExists);
        final SysUser sysUser = createUserEntity(user);
        saveNewUser(sysUser);
    }

    @Transactional
    @Override
    public SysUser findByEmail(UserRegisterInput user) {
        return userRepository.findByUsername(user.email()).orElse(null);
    }

    @Transactional
    @Override
    public SysUser findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    @Override
    public void saveUser(SysUser user) {
        userRepository.save(user);
    }

    private static void validateUserExists(SysUser userExists) {
        if (userExists != null) throw new UsuarioExistenteException("Email ja cadastrado.");
    }

    private String encoderPassword(String senha) {
        return passwordEncoder.encode(senha);
    }

    private SysUser createUserEntity(UserRegisterInput user) {
        return SysUser.builder()  //
                .descricao(null)  //
                .email(user.email())  //
                .username(user.nome())  //
                .password(encoderPassword(user.senha()))  //
                .build();
    }

    private void saveNewUser(SysUser sysUser) {
        userRepository.save(sysUser);
    }
}
