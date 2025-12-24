package com.security.authservice;

import com.security.authservice.manager.ResetPasswordEmailManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ResetPasswordEmailManager resetPasswordEmailManager;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password-success")
    public String forgotPassword(@RequestParam("email") String email) {
        resetPasswordEmailManager.enviar(email);
        return "forgot-password-success";
    }
}
