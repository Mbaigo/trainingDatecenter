package com.mbaigo.trainingtools.training_tools.domain;

import com.mbaigo.trainingtools.training_tools.user.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Trainer extends Utilisateur {
    private String certification;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Training> trainingList;
}

