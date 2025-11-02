package com.mbaigo.trainingtools.training_tools.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ModuleQuiz extends AbstractQuiz{
    @OneToOne
    @JoinColumn(name = "module_id")
    private Module module;

    //Liste des questions du quiz
    @OneToMany(mappedBy = "moduleQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
    public void addQuestion(Question q) {
        questions.add(q);
        q.setModuleQuiz(this);
    }
}
