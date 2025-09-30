package com.mbaigo.trainingTools.training_tools.beans;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Trainer implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mailAdress;
    private String level;
    private String skills;
    @OneToMany
    private List<Training> trainingList;
}
