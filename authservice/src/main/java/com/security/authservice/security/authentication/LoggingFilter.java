package com.security.authservice.security.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(0)
public class LoggingFilter extends OncePerRequestFilter {

    public LoggingFilter(OAuth2AuthorizationService authorizationService) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Aqui você pode logar informações de request
        System.out.println(">>> REQUEST URI: " + request.getRequestURI());

        // Se precisar do redirect_uri de alguma autorização específica,
        // você pode recuperar a autorização via token ou id:
        // OAuth2Authorization auth = authorizationService.findById(...);

        filterChain.doFilter(request, response);
    }
}
