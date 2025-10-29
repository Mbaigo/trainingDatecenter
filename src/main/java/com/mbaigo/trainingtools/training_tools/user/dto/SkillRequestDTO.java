package com.mbaigo.trainingtools.training_tools.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class SkillRequestDTO {
    @NotBlank(message = "Le nom de la compétence est requis.")
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 500)
    private String description;
}
