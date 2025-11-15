package com.security.authservice.security.authorizationserver;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class OAuth2AuthorizationQueryServiceImpl implements OAuth2AuthorizationQueryService {

    private final JdbcOperations jdbcTemplate;
    private final RowMapper<RegisteredClient> registeredClientRowMapper;
    private final RowMapper<OAuth2Authorization> oAuth2AuthorizationRowMapper;

    private static final String LIST_AUTHORIZED_CLIENT = """
            SELECT rc.*
            FROM oauth2_authorization_consent c
            INNER JOIN oauth2_registered_client rc
                ON rc.id = c.registered_client_id
            WHERE c.principal_name = ?
            """;

    private static final String LIST_AUTHORIZATIONS_QUERY = """
            select a.*
            from oauth2_authorization a
            inner join oauth2_registered_client c on c.id = a.registered_client_id
            where a.principal_name = ?
              and c.client_id = ?
            """;


    public OAuth2AuthorizationQueryServiceImpl(JdbcOperations jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.registeredClientRowMapper = new JdbcRegisteredClientRepository.RegisteredClientRowMapper();
        this.oAuth2AuthorizationRowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(registeredClientRepository);
    }

    @Override
    public List<RegisteredClient> listClientsWithConsent(String principalName) {
        return this.jdbcTemplate.query(LIST_AUTHORIZED_CLIENT, registeredClientRowMapper, principalName);
    }

    @Override
    public List<OAuth2Authorization> listAuthorizations(String principalName, String clientId) {
        return this.jdbcTemplate.query(LIST_AUTHORIZATIONS_QUERY, oAuth2AuthorizationRowMapper, principalName, clientId);
    }
}
