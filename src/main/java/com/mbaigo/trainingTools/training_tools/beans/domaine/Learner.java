package com.mbaigo.trainingTools.training_tools.beans.domaine;

import com.mbaigo.trainingTools.training_tools.beans.transaction.InscriptionTraining;
import com.mbaigo.trainingTools.training_tools.beans.transaction.Payment;
import com.mbaigo.trainingTools.training_tools.beans.transaction.Progression;
import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
