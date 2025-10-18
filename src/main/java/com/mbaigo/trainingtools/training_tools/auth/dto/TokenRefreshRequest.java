package com.mbaigo.trainingtools.training_tools.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}
