package com.mbaigo.trainingtools.training_tools.user.dto;


import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class ProfilRequest {
    private String certification;
    private String parcours;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;
}
