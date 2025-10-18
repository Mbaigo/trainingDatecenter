package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.mbaigo.trainingtools.training_tools.domain.Training;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Trainer extends Utilisateur {
    @OneToOne(mappedBy = "trainer", cascade = CascadeType.ALL)
    private Profil profil;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Training> trainingList;
}

