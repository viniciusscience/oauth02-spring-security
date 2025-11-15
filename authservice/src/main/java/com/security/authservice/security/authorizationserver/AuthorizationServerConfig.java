package com.security.authservice.security.authorizationserver;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.security.authservice.security.jwt.JwtKeyStoreProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {


    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(customizer -> customizer.loginPage("/login").permitAll())
                .with(authorizationServerConfigurer, Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public AuthorizationServerSettings authorizationServerSettings(AlgaFoodSecurityProperties properties) {
        return AuthorizationServerSettings.builder()
                .issuer(properties.getProviderUrl())
                .build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder, JdbcOperations jdbcOperations) {

   /*     RegisteredClient algafoodWeb =
                RegisteredClient.withId("2")
                        .clientId("algafood-web")
                        .clientSecret(passwordEncoder.encode("backend123"))
                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        ).authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .scope("READ")
                        .scope("WRITE")
                        .tokenSettings(TokenSettings.builder()
                                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                                .accessTokenTimeToLive(Duration.ofMinutes(30)).build()
                        )
                        .redirectUri("http://localhost:8080/authorized")
                        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                        .build();*/

        return new JdbcRegisteredClientRepository(jdbcOperations);
    }

    @Bean
    public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(JwtKeyStoreProperties jwtKeyStoreProperties) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, JOSEException {
        char[] keyStorePass = jwtKeyStoreProperties.getPassword().toCharArray();
        String keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

        Resource jksLocation = jwtKeyStoreProperties.getJksLocation();
        InputStream inputStream = jksLocation.getInputStream();

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, keyStorePass);

        RSAKey rsakey = RSAKey.load(keyStore, keyPairAlias, keyStorePass);

        return new ImmutableJWKSet<>(new JWKSet(rsakey));

    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> customizer() {
        return context -> {
            var authentication = context.getPrincipal();
            var user = authentication.getPrincipal();
            context.getClaims().claim("vinicius", user.toString());
        };
    }

    @Bean
    public OAuth2AuthorizationConsentService consentService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, registeredClientRepository);
    }

    @Bean
    public OAuth2AuthorizationQueryService authorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        return new OAuth2AuthorizationQueryServiceImpl(jdbcOperations, registeredClientRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

