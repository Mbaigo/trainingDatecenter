package com.mbaigo.trainingtools.training_tools.user.entities.users;
import com.mbaigo.trainingtools.training_tools.transaction.InscriptionTraining;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.LearningPlan;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @Getter @Setter @NoArgsConstructor @Builder
public class Learner extends Utilisateur {
        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<InscriptionTraining> inscriptions = new ArrayList<>();
        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<LearningPlan> learningPlans = new ArrayList<>();

}
