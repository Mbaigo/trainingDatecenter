package com.mbaigo.trainingtools.training_tools.auth.dto;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
