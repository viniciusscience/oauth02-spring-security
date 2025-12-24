package com.security.authservice.service;

import com.security.authservice.dto.UserRegisterInput;
import com.security.authservice.entity.SysUser;


public interface UserService {
    void createUser(UserRegisterInput user);
    SysUser findByEmail(UserRegisterInput user);
    SysUser findByEmail(String email);
    void saveUser(SysUser user);
}
