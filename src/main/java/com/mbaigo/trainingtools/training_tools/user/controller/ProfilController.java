package com.mbaigo.trainingtools.training_tools.user.controller;

import com.mbaigo.trainingtools.training_tools.config.ApiPathProperties;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireAdminRole;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireTrainerOrAdmin;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireTrainerRole;
import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilResponseDto;
import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;
import com.mbaigo.trainingtools.training_tools.user.mappers.ProfilMapper;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathProperties.BASE_API+"user/profils")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfilController {
    private final ProfilService profilService;
    @RequireTrainerRole
    @PostMapping("/addProfil")
    @Operation(summary = "Créer un profil pour l'utilisateur connecté")
    public ResponseEntity<ProfilResponseDto> createProfil(@Valid @RequestBody ProfilRequest profil) {
        return ResponseEntity.ok(ProfilMapper.toDTO(profilService.createProfilForUser(profil)));
    }
    @RequireTrainerRole
    @PutMapping("/{profilId}")
    public ResponseEntity<Profil> updateProfil(@PathVariable Long id, @RequestBody ProfilRequest profil) {
        return ResponseEntity.ok(profilService.updateProfilForUser(id, profil));
    }
    @RequireTrainerOrAdmin
    @PostMapping("/{profilId}/experience")
    public ResponseEntity<Experience> setExperienceToProfil(@PathVariable Long id, @RequestBody ExperienceRequest experienceRequest) {
        return ResponseEntity.ok(profilService.addExperienceToProfil(id, experienceRequest));
    }

    @RequireTrainerOrAdmin
    @PostMapping("/{profilId}/speciality")
    public ResponseEntity<Speciality> setSpecialityToProfil(@PathVariable Long profilId, @RequestBody SpecialityRequest specialityRequest) {
        return ResponseEntity.ok(profilService.addSpecialityToProfil(profilId, specialityRequest));
    }

    @RequireAdminRole
    @GetMapping
    public ResponseEntity<List<Profil>> getAllProfils() {
        return ResponseEntity.ok(profilService.findAllProfils());
    }
}
