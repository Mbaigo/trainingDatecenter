package com.mbaigo.trainingtools.training_tools.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //Le score pour passer le quiz
    private  Double scoreToPass;
    //Le score obtenu pour le quiz
    private Double scoringWeight;

}
