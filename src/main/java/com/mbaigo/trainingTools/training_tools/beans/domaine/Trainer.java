package com.mbaigo.trainingTools.training_tools.beans.domaine;

import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Trainer extends Utilisateur {
    private String certification;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Training> trainingList;
}
