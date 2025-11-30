package com.security.authservice.security.authorizationserver;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("algafood.auth")
public class AlgaFoodSecurityProperties {

    private String providerUrl;

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }
}
