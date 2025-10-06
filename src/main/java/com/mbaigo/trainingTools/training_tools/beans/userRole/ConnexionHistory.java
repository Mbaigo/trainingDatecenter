package com.mbaigo.trainingTools.training_tools.beans.userRole;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConnexionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateConnexion;
    private String adresseIP;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
