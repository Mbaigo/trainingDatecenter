package com.mbaigo.trainingtools.training_tools.user;

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
