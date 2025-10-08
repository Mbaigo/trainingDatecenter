package com.mbaigo.trainingtools.training_tools.admin;

import com.mbaigo.trainingtools.training_tools.user.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class MessageSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateEnvoi;
    private String sujet;
    @Column(columnDefinition = "TEXT")
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private Utilisateur expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Utilisateur destinataire;
}
