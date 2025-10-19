package com.mbaigo.trainingtools.training_tools.security.services.jwt_service;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt") @Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class JwtProperties {
    private String secret;
    private Long expirationMs;
    private Long refreshExpirationMs;

}
