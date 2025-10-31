package com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomaineResponseDTO {
    private Long id;
    private String name;
    private String description;
}

