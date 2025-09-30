package com.mbaigo.trainingTools.training_tools.beans;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity @AllArgsConstructor @Getter @Setter @NoArgsConstructor @Builder
public class Learner implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mailAdress;
    private String level;
    private String skills;
    @ManyToMany @JoinTable(
            name = "learnes_training",                        // nom de la table de jointure
            joinColumns = @JoinColumn(name = "learner_id"), // clé étrangère vers Etudiant
            inverseJoinColumns = @JoinColumn(name = "training_id") // clé étrangère vers Cours
    )
    private Set<Training> trainingList;
}
