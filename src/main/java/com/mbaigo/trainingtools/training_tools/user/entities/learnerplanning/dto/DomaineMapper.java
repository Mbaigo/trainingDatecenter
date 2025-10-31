package com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.Domaine;
import org.springframework.stereotype.Component;

@Component
public class DomaineMapper {

    public static DomaineResponseDTO toDTO(Domaine domaine) {
        if (domaine == null) return null;
        return DomaineResponseDTO.builder()
                .id(domaine.getId())
                .name(domaine.getName())
                .description(domaine.getDescription())
                .build();
    }

    public static Domaine toEntity(DomaineResponseDTO dto) {
        if (dto == null) return null;
        return Domaine.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
