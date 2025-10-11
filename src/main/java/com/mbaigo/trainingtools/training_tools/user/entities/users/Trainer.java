package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.mbaigo.trainingtools.training_tools.domain.Training;
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
    private String speciality;
}

