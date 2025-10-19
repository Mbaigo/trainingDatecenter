package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profils") @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String certification;
    //Details des parcours universitaires et professionnels
    private String parcours;
    private String photoUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "trainer_id")
    @JsonBackReference("trainer-profil")
    private Trainer trainer;
    /** Liste des expériences du profil */
    @OneToMany(mappedBy = "profil", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("profil-experiences")
    private List<Experience> experiences = new ArrayList<>();

    /** Liste des spécialités du profil */
    @OneToMany(mappedBy = "profil", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("profil-specialities")
    private List<Speciality> specialities = new ArrayList<>();

    public void addExperience(Experience exp) {
        experiences.add(exp);
        exp.setProfil(this);
    }

    public void addSpeciality(Speciality sp) {
        specialities.add(sp);
        sp.setProfil(this);
    }
}
