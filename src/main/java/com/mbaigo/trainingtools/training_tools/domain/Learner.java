package com.mbaigo.trainingtools.training_tools.domain;


import com.mbaigo.trainingtools.training_tools.transaction.InscriptionTraining;
import com.mbaigo.trainingtools.training_tools.transaction.Payment;
import com.mbaigo.trainingtools.training_tools.transaction.Progression;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @AllArgsConstructor @Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Learner extends Utilisateur {
        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<InscriptionTraining> inscriptions;

        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<Payment> paiements;

        @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
        private List<Progression> progressions;
}
