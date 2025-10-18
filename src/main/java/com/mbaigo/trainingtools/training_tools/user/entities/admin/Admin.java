package com.mbaigo.trainingtools.training_tools.user.entities.admin;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "admins") @Builder
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Admin extends Utilisateur {
    private String niveauAcces; // SUPER, STANDARD
    private String departement;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<TrainingValidation> validations;
}
