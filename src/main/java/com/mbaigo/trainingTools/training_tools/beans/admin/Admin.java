package com.mbaigo.trainingTools.training_tools.beans.admin;

import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
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
