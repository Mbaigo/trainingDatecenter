package com.mbaigo.trainingtools.training_tools.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
@AllArgsConstructor
public class UpdateUserDetailsRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String level;      // par ex: "Beginner", "Intermediate", "Advanced"     // USER, TRAINER, ADMIN
    private String specialty;  // pour Trainer
    private String department;
}
