package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.*;

@AllArgsConstructor @Setter @Getter @Builder @NoArgsConstructor
public class SpecialityRequest {
    private Long id;
    private String speciality;
    private Long profilId;
}
