package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.dto.LearningPlanRequestDTO;
import com.mbaigo.trainingtools.training_tools.user.dto.LearningPlanResponseDTO;
import com.mbaigo.trainingtools.training_tools.user.dto.SkillRequestDTO;
import com.mbaigo.trainingtools.training_tools.user.dto.SkillResponseDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Learner;
import com.mbaigo.trainingtools.training_tools.user.entities.users.LearningPlan;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LearningPlanMapper {

    public LearningPlanResponseDTO toResponseDTO(LearningPlan plan) {
        if (plan == null) return null;

        LearningPlanResponseDTO dto = new LearningPlanResponseDTO();
        dto.setId(plan.getId());
        dto.setDomain(plan.getDomain());
        dto.setGoal(plan.getGoal());
        dto.setDurationInMonths(plan.getDurationInMonths());
        dto.setHoursPerWeek(plan.getHoursPerWeek());
        dto.setCurrentLevel(plan.getCurrentLevel());
        dto.setTargetLevel(plan.getTargetLevel());
        dto.setPriority(plan.getPriority());
        dto.setCreatedAt(plan.getCreatedAt());

        if (plan.getSkills() != null) {
            dto.setSkills(
                    plan.getSkills().stream()
                            .map(this::toSkillResponseDTO)
                            .toList()
            );
        }

        return dto;
    }

    public SkillResponseDTO toSkillResponseDTO(Skill skill) {
        if (skill == null) return null;

        SkillResponseDTO dto = new SkillResponseDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        return dto;
    }

    public LearningPlan toEntity(LearningPlanRequestDTO dto, Learner learner) {
        if (dto == null) return null;

        LearningPlan plan = new LearningPlan();
        plan.setLearner(learner);
        plan.setDomain(dto.getDomain());
        plan.setGoal(dto.getGoal());
        plan.setDurationInMonths(dto.getDurationInMonths());
        plan.setHoursPerWeek(dto.getHoursPerWeek());
        plan.setCurrentLevel(dto.getCurrentLevel());
        plan.setTargetLevel(dto.getTargetLevel());
        plan.setPriority(dto.getPriority());

        if (dto.getSkills() != null) {
            List<Skill> skills = dto.getSkills().stream()
                    .map(skillDTO -> toSkillEntity(skillDTO, plan))
                    .toList();
            skills.forEach(skill -> skill.setLearningPlan(plan));
            plan.setSkills(skills);
        }

        return plan;
    }

    private Skill toSkillEntity(SkillRequestDTO dto, LearningPlan learner) {
        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill.setLearningPlan(learner); // ✅ chaque skill appartient au Learner
        return skill;
    }

}

