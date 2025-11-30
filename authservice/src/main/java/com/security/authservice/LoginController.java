package com.security.authservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController  {

    @GetMapping("/login")
    public String login(
            @RequestParam(value="redirect_uri", required=false) String redirectUri,
            @RequestParam(value="state", required=false) String state,
            HttpServletRequest request) {

        if (redirectUri != null) {
            request.getSession().setAttribute("OAUTH2_REDIRECT_URI", redirectUri);
        }
        if (state != null) {
            request.getSession().setAttribute("OAUTH2_STATE", state);
        }
        return "login";
    }
}
