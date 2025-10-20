package com.mbaigo.trainingtools.training_tools.user.controller;

import com.mbaigo.trainingtools.training_tools.config.ApiPathProperties;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireAdminRole;
import com.mbaigo.trainingtools.training_tools.controller.model.ApiResponse;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathProperties.BASE_API+"trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    /**
     * Récupérer tous les Trainers
     * Endpoint: GET /trainers
     */
    @GetMapping
    @RequireAdminRole
    @Operation(summary = "Listes des trainers")
    public ResponseEntity<ApiResponse<List<Trainer>>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAll();

        if (trainers == null || trainers.isEmpty()) {
            ApiResponse<List<Trainer>> response = new ApiResponse<>(
                    true,
                    "Aucun formateur trouvé",
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        ApiResponse<List<Trainer>> response = new ApiResponse<>(
                true,
                "Liste des formateurs récupérée avec succès",
                trainers
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/by-firstname") @RequireAdminRole
    @Operation(summary = "Recherche d'un Trainer sachant son prenom")
    public ResponseEntity<List<Trainer>> getByFirstName(@RequestParam String firstName) {
        List<Trainer> trainers = trainerService.findByFirstNameIgnoreCase(firstName.trim());
        if (trainers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/by-lastname") @RequireAdminRole
    @Operation(summary = "Recherche d'un trainer sachant son Nom")
    public ResponseEntity<List<Trainer>> getByLastName(@RequestParam String lastName) {
        List<Trainer> trainers = trainerService.findByLastNameIgnoreCase(lastName.trim());
        if (trainers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/by-mail") @RequireAdminRole
    @Operation(summary = "Recherche d'un trainer par email")
    public ResponseEntity<Trainer> getByMail(@RequestParam String mailAdress) {
        return trainerService.findByMailAdress(mailAdress.trim())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-phoneNumber") @RequireAdminRole
    @Operation(summary = "Recherche d'un trainer sachant son phoneNumber")
    public ResponseEntity<Trainer> getByPhoneNumber(@RequestParam String phoneNumber) {
        return trainerService.findByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
