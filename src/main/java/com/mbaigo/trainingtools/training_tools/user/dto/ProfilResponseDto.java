package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.*;

import java.util.List;

@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class ProfilResponseDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String certification;
    private String parcours;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;

    private List<ExperienceRequest> experiences;
    private List<SpecialityRequest> specialities;
}
