package com.mbaigo.trainingtools.training_tools.domain;

import com.mbaigo.trainingtools.training_tools.user.enumerations.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "questions") @Getter @Setter @AllArgsConstructor @Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String libelle;
    private String type; // QCM, TEXTE_LIBRE
    private QuestionType questionType;

    @ManyToOne
    private ModuleQuiz moduleQuiz;

    @ManyToOne
    private LeconQuiz leconQuiz;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reponse> reponses;
}
