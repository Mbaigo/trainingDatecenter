package com.mbaigo.trainingtools.training_tools.user.entities.users;

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
    //Details des parcours universitaires et professionnels
    private String parcours;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
