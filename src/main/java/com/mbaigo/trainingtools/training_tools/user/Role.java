package com.mbaigo.trainingtools.training_tools.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles") @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // APPRENANT, FORMATEUR, ADMIN
}
