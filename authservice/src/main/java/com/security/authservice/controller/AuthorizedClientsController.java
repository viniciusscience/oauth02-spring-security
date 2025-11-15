package com.security.authservice.controller;

import com.security.authservice.security.authorizationserver.OAuth2AuthorizationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorizedClientsController {

    private final OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService;
    private final RegisteredClientRepository clientRepository;
    private final OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService;
    private final OAuth2AuthorizationService oAuth2AuthorizationService;

    @GetMapping("/oauth2/authorized-clients")
    public String listClientsWithConsent(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();

        final var clients = oAuth2AuthorizationQueryService.listClientsWithConsent(username);
        model.addAttribute("clients", clients);
        return "authorized-clients";
    }

    @PostMapping("/oauth2/authorized-clients/revoke")
    public String revokeClients(Authentication authentication,
                                Model model,
                                @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId) throws AccessDeniedException {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String username = authentication.getName();
        RegisteredClient registeredClient = this.clientRepository.findByClientId(clientId);

        if (registeredClient == null) {
            throw new AccessDeniedException("Client with id");
        }

        OAuth2AuthorizationConsent consent =
                this.oAuth2AuthorizationConsentService.findById(
                        registeredClient.getId(),
                        username
                );
        List<OAuth2Authorization> oAuth2Authorizations = this.oAuth2AuthorizationQueryService.listAuthorizations(username, registeredClient.getClientId());
        if (consent != null) {
            this.oAuth2AuthorizationConsentService.remove(consent);
        }

        oAuth2Authorizations.forEach(this.oAuth2AuthorizationService::remove);
        return "redirect:/oauth2/authorized-clients";
    }
}
