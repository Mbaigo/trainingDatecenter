package com.mbaigo.trainingtools.training_tools.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private boolean correcte;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
