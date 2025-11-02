package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.Domaine;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.LearningPlanRequestDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.LearningPlanResponseDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.SkillResponseDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.LearningPlan;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.Skill;
import org.springframework.stereotype.Component;


@Component
public class LearningPlanMapper {

    public SkillResponseDTO toSkillResponseDTO(Skill skill) {
        if (skill == null) return null;

        SkillResponseDTO dto = new SkillResponseDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        return dto;
    }

    public static LearningPlanResponseDTO toDTO(LearningPlan plan) {
        if (plan == null) return null;

        return LearningPlanResponseDTO.builder()
                .id(plan.getId())
                .domaine(plan.getDomaine() != null ? plan.getDomaine().getName() : null)
                .goal(plan.getGoal())
                .durationInMonths(plan.getDurationInMonths())
                .hoursPerWeek(plan.getHoursPerWeek())
                .currentLevel(plan.getCurrentLevel())
                .targetLevel(plan.getTargetLevel())
                .priority(plan.getPriority())
                .createdAt(plan.getCreatedAt())
                .build();
    }

    public static LearningPlan toEntity(LearningPlanRequestDTO dto, Domaine domaine) {
        if (dto == null) return null;

        LearningPlan plan = new LearningPlan();
        plan.setDomaine(domaine);
        plan.setGoal(dto.getGoal());
        plan.setDurationInMonths(dto.getDurationInMonths());
        plan.setHoursPerWeek(dto.getHoursPerWeek());
        plan.setCurrentLevel(dto.getCurrentLevel());
        plan.setTargetLevel(dto.getTargetLevel());
        plan.setPriority(dto.getPriority());


        return plan;
    }



}

