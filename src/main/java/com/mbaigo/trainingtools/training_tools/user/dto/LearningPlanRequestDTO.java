package com.mbaigo.trainingtools.training_tools.user.dto;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Skill;
import lombok.*;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class LearningPlanRequestDTO {

    @NotBlank(message = "L’objectif est requis.")
    private String goal;

    private String domain;
    private Integer durationInMonths;
    private Integer hoursPerWeek;
    private String currentLevel;
    private String targetLevel;
    private Integer priority;

    @NotNull(message = "La date d’objectif est requise.")
    @Future(message = "La date d’objectif doit être dans le futur.")
    private LocalDate targetDate;

    private List<SkillRequestDTO> skills = new ArrayList<>();
}


