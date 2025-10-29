package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class LearningPlanResponseDTO {
    private Long id;
    private String domain;
    private String goal;
    private Integer durationInMonths;
    private Integer hoursPerWeek;
    private String currentLevel;
    private String targetLevel;
    private Integer priority;
    private LocalDateTime createdAt;
    private List<SkillResponseDTO> skills;
}

