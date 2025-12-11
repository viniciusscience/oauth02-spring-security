package com.security.authservice.security.http.filter;

import com.security.authservice.security.context.ServiceContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomLogoutFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 🔥 SE FOR OPTIONS → NÃO FAZ LOGOUT, APENAS SEGUE
            if (HttpMethod.OPTIONS.matches(request.getMethod())) {
                filterChain.doFilter(request, response);
                return;
            }

            if (request.getServletPath().equals("/logout")) {

                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }

                SecurityContextHolder.clearContext();

                String redirectUri = request.getParameter("redirect_uri");
                if (redirectUri == null) {
                    redirectUri = request.getHeader("referer");
                }

                if (redirectUri != null) {
                    response.sendRedirect(redirectUri);

                    return;
                }
            }

            filterChain.doFilter(request, response);

        } finally {
            ServiceContext.clear();
        }
    }
}

