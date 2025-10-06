package com.mbaigo.trainingTools.training_tools.beans.userRole;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profils") @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String bio;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
