package com.mbaigo.trainingtools.training_tools.user.controller;
import com.mbaigo.trainingtools.training_tools.auth.services.ConnexionHistoryService;

import com.mbaigo.trainingtools.training_tools.security.services.jwt_service.JwtUtil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.ConnexionHistory;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class ConnexionHistoryController {

    private final ConnexionHistoryService connexionHistoryService;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtil jwtUtil;

    /**
     * Récupère l’historique de connexion de l’utilisateur actuellement connecté.
     * Le token JWT est lu depuis le header Authorization.
     */
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ConnexionHistory>> getMyHistory(
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        List<ConnexionHistory> history = connexionHistoryService.getHistoriqueByUtilisateur(utilisateur);

        if (history.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(history);
    }

    /**
     * (Optionnel) Permet à un administrateur de consulter l’historique de tout utilisateur.
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ConnexionHistory>> getAllHistories() {
        List<ConnexionHistory> all = connexionHistoryService.getAllHistories();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }
}

