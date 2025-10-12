package com.mbaigo.trainingtools.training_tools.auth.dto;

import lombok.Data;

@Data
public class ProfilRequest {
    private String nom;
    private String prenom;
    //Details des parcours universitaires et professionnels
    private String parcours;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;
}
