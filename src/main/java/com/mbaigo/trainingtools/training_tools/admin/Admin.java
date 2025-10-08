package com.mbaigo.trainingtools.training_tools.admin;

import com.mbaigo.trainingtools.training_tools.user.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "utilisateur_id") @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Admin extends Utilisateur {
    private String niveauAcces; // SUPER, STANDARD

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<TrainingValidation> validations;
}
