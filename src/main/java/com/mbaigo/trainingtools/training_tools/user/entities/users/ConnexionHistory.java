package com.mbaigo.trainingtools.training_tools.user.entities.users;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private String device;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonBackReference
    private Utilisateur utilisateur;
}
