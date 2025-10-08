package com.mbaigo.trainingtools.training_tools.config.security;

import com.mbaigo.trainingtools.training_tools.beans.user_role.RefreshToken;
import com.mbaigo.trainingtools.training_tools.beans.user_role.Utilisateur;
import com.mbaigo.trainingtools.training_tools.dao.dao.factory.daoImpl.domaine.user_role.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.services.impl.jwt_service.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UtilisateurRepository userRepo;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil,
                          UtilisateurRepository userRepo, RefreshTokenService refreshTokenService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Utilisateur user, PasswordEncoder encoder) {
        if(userRepo.existsByUsername(user.getUsername())) return ResponseEntity.badRequest().body("Username déjà utilisé");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok("Utilisateur enregistré !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> creds) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(creds.get("username"), creds.get("password"))
        );
        Utilisateur user = userRepo.findByUsername(creds.get("username")).get();
        if(user.isTwoFactorEnabled()) {
            // générer challenge TOTP ici → renvoyer "2FA required"
            return ResponseEntity.ok(Map.of("2fa_required", true));
        }
        String token = jwtUtil.generateToken((UserDetails) auth.getPrincipal());
        RefreshToken rt = refreshTokenService.createRefreshToken(user);
        return ResponseEntity.ok(Map.of("token", token, "refreshToken", rt.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String,String> body) {
        String requestToken = body.get("refreshToken");
        return refreshTokenService.findByToken(requestToken)
                .flatMap(refreshTokenService::verifyExpiration)
                .map(rt -> {
                    String token = jwtUtil.generateToken(new UserPrincipal(rt.getUser()));
                    return ResponseEntity.ok(Map.of("token", token, "refreshToken", rt.getToken()));
                }).orElseThrow(() -> new RuntimeException("Refresh token invalide"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String,String> body) {
        Utilisateur user = userRepo.findByUsername(body.get("username"))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        refreshTokenService.deleteByUser(user);
        return ResponseEntity.ok("Déconnecté avec succès");
    }
}


