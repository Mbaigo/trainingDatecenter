package com.mbaigo.trainingtools.training_tools.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ressources") @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // VIDEO, PDF, CODE, LIEN
    private String url;

    @ManyToOne
    @JoinColumn(name = "lecon_id")
    private Lecon lecon;
}
