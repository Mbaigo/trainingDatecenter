package com.mbaigo.trainingTools.training_tools.app;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.services.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    /**
     * Ajouter un nouveau Trainer
     * Endpoint: POST /trainers
     * Corps: JSON représentant un Trainer
     */
    @PostMapping
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer) {
        if (trainer == null) {
            return ResponseEntity.badRequest().build();
        }
        Trainer created = trainerService.create(trainer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Récupérer tous les Trainers
     * Endpoint: GET /trainers
     */
    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAll();
        if (trainers == null || trainers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainers);
    }
}
