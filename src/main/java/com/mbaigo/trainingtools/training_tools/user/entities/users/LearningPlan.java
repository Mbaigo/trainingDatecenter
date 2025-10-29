package com.mbaigo.trainingtools.training_tools.user.entities.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class LearningPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "L’objectif du plan est obligatoire.")
    private String goal; // Exemple : "Devenir Développeur Spring Boot Junior"

    @NotNull(message = "La durée / deadline est obligatoire.")
    @Future(message = "La date d’objectif doit être dans le futur.")
    private LocalDate targetDate; // Exemple : 2025-12-31

    private String domain;
    private Integer durationInMonths;
    private Integer hoursPerWeek;
    private String currentLevel;
    private String targetLevel;
    private Integer priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // ✅ Un plan appartient à un seul Learner
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learner_id", unique = true)
    private Learner learner;

    // ✅ Liste des compétences à acquérir
    @OneToMany(mappedBy = "learningPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setLearningPlan(this);
    }
}

