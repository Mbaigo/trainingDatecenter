package com.mbaigo.trainingtools.training_tools.auth.dto;

import lombok.*;

@Setter @Getter @AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String username;
}

