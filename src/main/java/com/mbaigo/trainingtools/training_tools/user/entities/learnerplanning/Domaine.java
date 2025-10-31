package com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "domaines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du domaine est obligatoire")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "domaine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LearningPlan> learningPlans = new ArrayList<>();

    // 🔹 Méthode utilitaire (optionnelle)
    public void addLearningPlan(LearningPlan plan) {
        learningPlans.add(plan);
        plan.setDomaine(this);
    }
}

