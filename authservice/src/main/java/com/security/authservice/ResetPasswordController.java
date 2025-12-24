package com.security.authservice;

import com.security.authservice.dto.ChangePasswordInput;
import com.security.authservice.forgot.password.email.utils.TokenEmailManager;
import com.security.authservice.manager.ChangePasswordManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ChangePasswordManager changePasswordManager;
    private final TokenEmailManager tokenEmailManager;

    @GetMapping("/reset-password")
    public String forgotPassword(@RequestParam String token) {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "As senhas não coincidem");
            return "reset-password";
        }

        final var changePasswordInput = ChangePasswordInput.builder()
                .token(token)
                .senha(password)
                .confirmaSenha(confirmPassword)
                .build();
        changePasswordManager.alterar(changePasswordInput);
        tokenEmailManager.markTokenAsUsed(token);

        model.addAttribute("success", "Senha alterada com sucesso!");
        return "reset-password";
    }
}
