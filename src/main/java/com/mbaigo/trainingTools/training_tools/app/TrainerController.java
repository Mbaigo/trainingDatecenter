package com.mbaigo.trainingTools.training_tools.app;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainerDto;
import com.mbaigo.trainingTools.training_tools.services.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<TrainerDto> addTrainer(@RequestBody TrainerDto trainer) {
        if (trainer == null) {
            return ResponseEntity.badRequest().build();
        }
        TrainerDto created = trainerService.save(trainer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Récupérer tous les Trainers
     * Endpoint: GET /trainers
     */
    @GetMapping
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
        List<TrainerDto> trainers = trainerService.findAll();
        if (trainers == null || trainers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/by-id")
    public Optional<TrainerDto> getById(@RequestParam Long id) {
        return trainerService.findById(id);
    }

    @GetMapping("/by-firstname")
    public List<TrainerDto> getByFirstName(@RequestParam String firstName) {
        return trainerService.findByFirstNameIgnoreCase(firstName);
    }

    @GetMapping("/by-lastname")
    public List<TrainerDto> getByLastName(@RequestParam String lastName) {
        return trainerService.findByLastNameIgnoreCase(lastName);
    }

    @GetMapping("/by-mail")
    public Optional<TrainerDto> getByMail(@RequestParam String mailAdress) {
        return trainerService.findByMailAdress(mailAdress);
    }

}
