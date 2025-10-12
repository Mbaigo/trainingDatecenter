package com.mbaigo.trainingtools.training_tools.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterFirstRequest {

    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "L'adresse email est obligatoire")
    private String password;

    @NotBlank  @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String confirmPassword;

    @NotBlank
    private String role; // "TRAINER","LEARNER","ADMIN"

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String username;
}
