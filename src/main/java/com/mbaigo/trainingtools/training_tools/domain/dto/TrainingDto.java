package com.mbaigo.trainingtools.training_tools.domain.dto;


import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;

import java.time.LocalDateTime;

public class TrainingDto {
    private String title;
    private String contenuVideoUrl; // URL ou chemin vers la vidéo
    private LocalDateTime datePublication;
    private Trainer auteur; // association vers la classe Auteur
}
