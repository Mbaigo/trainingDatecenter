package com.mbaigo.trainingtools.training_tools.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LeconQuiz extends AbstractQuiz {
    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lecon lecon;

    //Liste des questions du quiz
    @OneToMany(mappedBy = "leconQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question q) {
        questions.add(q);
        q.setLeconQuiz(this);
    }
}
