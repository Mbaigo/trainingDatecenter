package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ExperienceMapper {

    // 🔹 DTO → Entity
    public Experience toEntity(ExperienceRequest dto, Profil profil) {
        if (dto == null) return null;
        Experience experience = new Experience();
        experience.setJobTitle(dto.getJobTitle());
        experience.setCompany(dto.getCompany());
        experience.setLocation(dto.getLocation());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setJobDescription(dto.getJobDescription());
        // on associe le profil fourni (s'il existe)
        if (profil != null) {
            experience.setProfil(profil);
        }
        return experience;
    }

    // 🔹 Entity → DTO
    public ExperienceRequest toDto(Experience entity) {
        if (entity == null) return null;
        return ExperienceRequest.builder()
                .jobTitle(entity.getJobTitle())
                .company(entity.getCompany())
                .location(entity.getLocation())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .jobDescription(entity.getJobDescription())
                .build();
    }

    public List<ExperienceRequest> toDtoList(List<Experience> experiences) {
        if (experiences == null || experiences.isEmpty()) return Collections.emptyList();
        return experiences.stream()
                .map(this::toDto)
                .toList();
    }

    public List<Experience> toEntityList(List<ExperienceRequest> dtos, Profil profil) {
        if (dtos == null || dtos.isEmpty()) return Collections.emptyList();
        return dtos.stream()
                .map(dto -> toEntity(dto, profil))
                .toList();
    }

}
