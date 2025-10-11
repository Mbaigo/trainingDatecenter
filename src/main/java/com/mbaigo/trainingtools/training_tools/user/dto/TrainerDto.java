package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class TrainerDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mailAdress;
    private String level;
    private String skills;
}
