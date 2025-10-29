package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class SkillResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
}

