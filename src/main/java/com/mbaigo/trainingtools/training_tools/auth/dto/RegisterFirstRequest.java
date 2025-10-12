package com.mbaigo.trainingtools.training_tools.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterFirstRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String role; // "TRAINER","LEARNER","ADMIN"

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String username;
}
