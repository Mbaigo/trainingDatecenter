package com.mbaigo.trainingtools.training_tools.admin;

import com.mbaigo.trainingtools.training_tools.domain.Training;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class TrainingValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateValidation;
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    private boolean valide;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
