package com.mbaigo.trainingtools.training_tools.user.controller;

import com.mbaigo.trainingtools.training_tools.config.annotations.RequireAdminRole;
import com.mbaigo.trainingtools.training_tools.config.annotations.RequireTrainerRole;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training/api/profils")
@RequiredArgsConstructor
public class ProfilController {

    private final ProfilService profilService;
    @RequireTrainerRole
    @PostMapping("/{userId}")
    public ResponseEntity<Profil> createProfil(@PathVariable Long userId,@RequestBody ProfilRequest profil) {
        return ResponseEntity.ok(profilService.createProfilForUser(userId,profil));
    }
    @RequireTrainerRole
    @PutMapping("/{id}")
    public ResponseEntity<Profil> updateProfil(@PathVariable Long id, @RequestBody ProfilRequest profil) {
        return ResponseEntity.ok(profilService.updateProfilForUser(id, profil));
    }
    @RequireAdminRole
    @GetMapping
    public ResponseEntity<List<Profil>> getAllProfils() {
        return ResponseEntity.ok(profilService.findAllProfils());
    }
}
