package com.mbaigo.trainingtools.training_tools.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder @NoArgsConstructor
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}
