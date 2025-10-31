package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.SkillRequestDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.SkillResponseDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Skill;

import java.time.format.DateTimeFormatter;

public class SkillMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static Skill toEntity(SkillRequestDTO dto) {
        return Skill.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static SkillResponseDTO toDTO(Skill entity) {
        return SkillResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().format(formatter) : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(formatter) : null)
                .build();
    }
}

