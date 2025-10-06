package com.mbaigo.trainingTools.training_tools.beans.domaine;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags") @AllArgsConstructor @Setter @NoArgsConstructor @Getter @Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToMany(mappedBy = "tags")
    private Set<Training> cours = new HashSet<>();
}
