package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "specialities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Speciality {

    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profil_id")
    @JsonBackReference("profil-specialities")
    private Profil profil;
}
