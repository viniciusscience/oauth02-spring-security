package com.security.authservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController  {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("versao", "1.0.7");
        model.addAttribute("mensagem", "Bem-vindo ao sistema!");
        return "login";
    }
}
