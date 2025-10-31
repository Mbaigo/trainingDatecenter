package com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningPlanRequestDTO {

    @NotBlank(message = "Le domaine est obligatoire")
    private String domainName; // 🔹 nom du domaine existant à rattacher

    @NotBlank(message = "L'objectif du plan est obligatoire")
    private String goal;

    @NotNull(message = "La durée en mois est obligatoire")
    @Min(value = 1, message = "La durée doit être au moins 1 mois")
    private Integer durationInMonths;

    @NotNull(message = "Le nombre d'heures par semaine est obligatoire")
    @Min(value = 1, message = "Le nombre d'heures par semaine doit être positif")
    private Integer hoursPerWeek;

    @NotBlank(message = "Le niveau actuel est obligatoire")
    private String currentLevel;

    @NotBlank(message = "Le niveau visé est obligatoire")
    private String targetLevel;

    @NotNull(message = "La priorité est obligatoire")
    @Min(1)
    @Max(5)
    private Integer priority;

    private List<SkillRequestDTO> skills;
}
