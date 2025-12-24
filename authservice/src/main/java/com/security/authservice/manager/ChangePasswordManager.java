package com.security.authservice.manager;

import com.security.authservice.dto.ChangePasswordInput;

public interface ChangePasswordManager {
    void alterar(ChangePasswordInput changePasswordInput);
}
