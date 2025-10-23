package com.mbaigo.trainingtools.training_tools.user.controller;

import com.mbaigo.trainingtools.training_tools.config.ApiPathProperties;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireAdminRole;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireAuthenticated;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireTrainerOrAdmin;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireTrainerRole;
import com.mbaigo.trainingtools.training_tools.controller.model.ApiResponse;
import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilResponseDto;
import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;
import com.mbaigo.trainingtools.training_tools.user.mappers.ProfilMapper;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import com.mbaigo.trainingtools.training_tools.user.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiPathProperties.BASE_API+"user/profils")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfilController {
    private final ProfilService profilService;
    private final TrainerService trainerService;
    @RequireTrainerRole
    @PostMapping("/addProfil")
    @Operation(summary = "Créer un profil pour l'utilisateur connecté")
    public ResponseEntity<ProfilResponseDto> createProfil(@Valid @RequestBody ProfilRequest profil) {
        return ResponseEntity.ok(ProfilMapper.toDTO(profilService.createProfilForUser(profil)));
    }
    @RequireTrainerRole
    @PutMapping("/{profilId}")
    @Operation(summary = "MAJ un profil pour l'utilisateur connecté")
    public ResponseEntity<Profil> updateProfil(@PathVariable Long id, @RequestBody ProfilRequest profil) {
        return ResponseEntity.ok(profilService.updateProfilForUser(id, profil));
    }
    @RequireTrainerOrAdmin
    @PostMapping("/experience")
    @Operation(summary = "Créer une experience pour le profil de l'utilisateur connecté")
    public ResponseEntity<Experience> setExperienceToProfil(@RequestBody ExperienceRequest experienceRequest) {
        return ResponseEntity.ok(profilService.addExperienceToProfil(experienceRequest));
    }

    @RequireTrainerOrAdmin
    @PostMapping("/speciality")
    @Operation(summary = "Créer une speciality pour le profil de l'utilisateur connecté")
    public ResponseEntity<Speciality> setSpecialityToProfil(@RequestBody SpecialityRequest specialityRequest) {
        // 2️⃣ Délègue au service (sans passer de profilId)
        Speciality speciality = profilService.addSpecialityToProfil(specialityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(speciality); }

    @RequireAdminRole
    @GetMapping
    @Operation(summary = "Tous les profils utilisateurs")
    public ResponseEntity<ApiResponse<List<Profil>>> getAllProfils() {

        List<Profil> profils = profilService.findAllProfils();
        if (profils == null || profils.isEmpty()) {
            ApiResponse<List<Profil>> response = new ApiResponse<>(
                    true,
                    "Aucun Profil trouvé",
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        ApiResponse<List<Profil>> response = new ApiResponse<>(
                true,
                "Profils récupérés avec succès",
                profils
        );

        return ResponseEntity.ok(response);
    }

    @RequireAuthenticated
    @GetMapping("/by-email")
    @Operation(summary = "Le profil d'un utilisateur avec son email")
    public ResponseEntity<ApiResponse<Optional<Profil>>> getProfilByUtilisateurEmail(@RequestParam @Email String email) {
        Optional<Profil> profil = profilService.findByUtilisateurEmail(email);
        if (profil == null || profil.isEmpty()) {
            ApiResponse<Optional<Profil>> response = new ApiResponse<>(
                    true,
                    "Aucun Profil trouvé",
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        ApiResponse<Optional<Profil>> response = new ApiResponse<>(
                true,
                "Profil récupéré avec succès",
                profil
        );

        return ResponseEntity.ok(response);
    }

    @RequireAuthenticated
    @GetMapping("/by-parcours")
    @Operation(summary = "Le profil des utilisateurs avec un parcours donné")
    public ResponseEntity<ApiResponse<List<Profil>>> getProfilByParcours(@RequestParam String parcour) {
        List<Profil> profils = profilService.findProfilByParcours(parcour);
        if (profils == null || profils.isEmpty()) {
            ApiResponse<List<Profil>> response = new ApiResponse<>(
                    true,
                    "Aucun Profil trouvé",
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        ApiResponse<List<Profil>> response = new ApiResponse<>(
                true,
                "Liste des formateurs récupérée avec succès",
                profils
        );

        return ResponseEntity.ok(response);
    }


    @RequireAuthenticated
    @GetMapping("/by-certification")
    @Operation(summary = "Le profil des utilisateurs avec une certification donnée")
    public ResponseEntity<ApiResponse<List<Profil>>> getProfilByCertificate(@RequestParam String certification) {
        List<Profil> profils = profilService.findProfilByCertification(certification);
        if (profils == null || profils.isEmpty()) {
            ApiResponse<List<Profil>> response = new ApiResponse<>(
                    true,
                    "Aucun Profil trouvé",
                    null
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        ApiResponse<List<Profil>> response = new ApiResponse<>(
                true,
                "Liste des formateurs récupérée avec succès",
                profils
        );

        return ResponseEntity.ok(response);

    }
}
