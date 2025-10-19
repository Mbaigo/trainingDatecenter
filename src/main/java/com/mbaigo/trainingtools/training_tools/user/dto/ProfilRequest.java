package com.mbaigo.trainingtools.training_tools.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @Builder
public class ProfilRequest {
    private String certification;
    private String parcours;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;
}
