package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
public class ProfilRequest {
    private String certification;
    private String nom;
    private String prenom;
    private String email;
    //Details des parcours universitaires et professionnels
    private String parcours;
//    @JsonIgnore
//    private List<SpecialityRequest> specialities;
//    @JsonIgnore
//    private List<ExperienceRequest> experiences;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;
}
