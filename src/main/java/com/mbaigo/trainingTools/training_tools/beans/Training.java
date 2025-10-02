package com.mbaigo.trainingTools.training_tools.beans;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity @Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Training {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // identifiant (à remplacer par @Id si c'est une entité JPA)
    private String title;
    private String contenuVideoUrl; // URL ou chemin vers la vidéo
    private LocalDateTime datePublication;
    private Boolean status;
    private LocalDateTime createdAt;
    @ManyToOne
    private Trainer auteur; // association vers la classe Auteur
}
