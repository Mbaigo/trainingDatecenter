package com.mbaigo.trainingTools.training_tools.beans.admin;

import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages_support")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class MessageSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sujet;
    @Column(columnDefinition = "TEXT")
    private String contenu;
    private LocalDateTime dateEnvoi;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private Utilisateur expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Utilisateur destinataire;
}
