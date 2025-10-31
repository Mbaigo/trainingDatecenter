package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.LearningPlan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la compétence est obligatoire.")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères.")
    private String name;

    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learning_plan_id")
    private LearningPlan learningPlan;
}

