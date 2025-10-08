package com.mbaigo.trainingtools.training_tools.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lecons") @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Lecon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Column(columnDefinition = "TEXT")
    private String contenu;
    private int ordre;
    private String type; // VIDEO, TEXTE, EXERCICE

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "lecon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ressource> ressources;
}
