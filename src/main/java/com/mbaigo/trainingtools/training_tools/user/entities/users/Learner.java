package com.mbaigo.trainingtools.training_tools.user.entities.users;
import com.mbaigo.trainingtools.training_tools.transaction.InscriptionTraining;
import com.mbaigo.trainingtools.training_tools.transaction.Payment;
import com.mbaigo.trainingtools.training_tools.transaction.Progression;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @AllArgsConstructor @Getter @Setter @NoArgsConstructor @Builder
public class Learner extends Utilisateur {
        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<InscriptionTraining> inscriptions;

        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<Payment> paiements;

        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<Progression> progressions;
}
