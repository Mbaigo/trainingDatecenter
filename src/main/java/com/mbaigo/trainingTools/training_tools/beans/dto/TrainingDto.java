package com.mbaigo.trainingTools.training_tools.beans.dto;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;

import java.time.LocalDateTime;

public class TrainingDto {
    private String title;
    private String contenuVideoUrl; // URL ou chemin vers la vidéo
    private LocalDateTime datePublication;
    private Trainer auteur; // association vers la classe Auteur
}
