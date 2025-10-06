package com.mbaigo.trainingTools.training_tools.beans.userRole;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles") @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // APPRENANT, FORMATEUR, ADMIN
}
