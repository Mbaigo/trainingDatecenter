package com.mbaigo.trainingtools.training_tools.auth.controller;

import com.mbaigo.trainingtools.training_tools.auth.dto.JwtResponse;
import com.mbaigo.trainingtools.training_tools.auth.dto.LoginRequest;
import com.mbaigo.trainingtools.training_tools.auth.dto.RegisterRequest;
import com.mbaigo.trainingtools.training_tools.auth.dto.TokenRefreshRequest;
import com.mbaigo.trainingtools.training_tools.auth.services.AuthService;
import com.mbaigo.trainingtools.training_tools.exception.TokenRefreshException;
import com.mbaigo.trainingtools.training_tools.security.RefreshTokenService;
import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/training/api/auth") @AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody RegisterRequest request) {
        Utilisateur user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse accessToken = authService.login(request, "127.0.0.1", "Browser");
        Utilisateur user = authService.getUserByEmail(request.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        JwtResponse jwtResponse = JwtResponse.builder()
                .token(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .build();

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token invalide"));

        refreshTokenService.verifyExpiration(refreshToken);

        String newAccessToken = authService.generateTokenFromEmail(refreshToken.getUtilisateur().getEmail());
        String newRefreshToken = refreshTokenService.createRefreshToken(refreshToken.getUtilisateur()).getToken();

        JwtResponse response = JwtResponse.builder()
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam Long userId) {
        refreshTokenService.deleteUtilisateurById(userId);
        return ResponseEntity.noContent().build();
    }
}


