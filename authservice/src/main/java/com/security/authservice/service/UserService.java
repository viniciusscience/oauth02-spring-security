package com.security.authservice.service;

import com.security.authservice.dto.UserRegisterInput;

public interface UserService {
    void createUser(UserRegisterInput user);
}
