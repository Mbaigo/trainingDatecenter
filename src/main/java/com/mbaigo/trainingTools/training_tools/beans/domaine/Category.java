package com.mbaigo.trainingTools.training_tools.beans.domaine;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories") @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @OneToMany(mappedBy = "categorie")
    private List<Training> cours;
}
