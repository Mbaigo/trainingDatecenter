package com.mbaigo.trainingtools.training_tools.security.services.jwt_service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.jwt") @Data
public class JwtProperties {
    private String secret;
    private Long expirationMs;
    private Long refreshExpirationMs;

}

