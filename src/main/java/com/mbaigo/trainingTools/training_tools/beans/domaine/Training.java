package com.mbaigo.trainingTools.training_tools.beans.domaine;

import com.mbaigo.trainingTools.training_tools.beans.transaction.EvaluateTraining;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String niveau; // Débutant, Intermédiaire, Avancé
    private BigDecimal prix;
    private LocalDateTime createdAt;
    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "Trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categorie;

    @ManyToMany
    @JoinTable(name = "training_tags",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> modules;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private List<EvaluateTraining> evaluations;
}
