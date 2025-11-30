package com.security.authservice.security.resourceserver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequestMapping
public class ResourceServerConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowBackSlash(true);
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/oauth2/**").permitAll()
                        .requestMatchers("/.well-known/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService(passwordEncoder()))
                .formLogin(customizer ->
                        customizer.loginPage("/login")
                                .successHandler((request, response, authentication) -> {
                                    response.sendRedirect("http://localhost:4200/inicio");
                                }))
                .logout(logout -> logout.logoutSuccessUrl("/login"))
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt -> {
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .setSharedObject(HttpFirewall.class,  firewall);;
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        var role = new SimpleGrantedAuthority("ROLE_USER");
        var role1 = new SimpleGrantedAuthority("ROLE_ADMIN");
        var role2 = new SimpleGrantedAuthority("ROLE_PERSONAL");

        var user = new User("user", passwordEncoder.encode("user"), List.of(role, role1, role2));
        var admin = new User("admin", passwordEncoder.encode("admin"), List.of(role2));

        return new InMemoryUserDetailsManager(user, admin);

    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                return Collections.emptySet();
            }

            var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
            var grantendAuthorities = authoritiesConverter.convert(jwt);

            grantendAuthorities.addAll(
                    authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                     .toList());

            return grantendAuthorities;
        });

        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
