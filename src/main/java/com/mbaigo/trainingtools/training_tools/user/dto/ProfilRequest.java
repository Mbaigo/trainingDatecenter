package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfilRequest {
    private String certification;
    private String nom;
    private String prenom;
    private String email;
    //Details des parcours universitaires et professionnels
    private String parcours;
    private List<SpecialityRequest> specialities;
    private List<ExperienceRequest> experiences;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;
}
