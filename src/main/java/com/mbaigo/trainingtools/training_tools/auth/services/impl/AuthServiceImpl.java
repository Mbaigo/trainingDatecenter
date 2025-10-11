package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.dto.JwtResponse;
import com.mbaigo.trainingtools.training_tools.auth.services.AuthService;
import com.mbaigo.trainingtools.training_tools.auth.services.ConnexionHistoryService;
import com.mbaigo.trainingtools.training_tools.security.services.jwt_service.JwtUtil;
import com.mbaigo.trainingtools.training_tools.auth.dto.LoginRequest;
import com.mbaigo.trainingtools.training_tools.auth.dto.RegisterRequest;
import com.mbaigo.trainingtools.training_tools.security.RefreshTokenService;
import com.mbaigo.trainingtools.training_tools.user.entities.admin.Admin;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Role;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final ConnexionHistoryService connexionHistoryService;
    public Utilisateur register(RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Utilisateur user;
        switch (request.getRole().toUpperCase()) {
            case "TRAINER":
                user = new Trainer();
                ((Trainer) user).setSpeciality(request.getSpecialty());
                break;
            case "ADMIN":
                user = new Admin();
                ((Admin) user).setDepartement(request.getDepartment());
                break;
            default:
                user = new Utilisateur();
        }

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLevel(request.getLevel());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(new Role(null, "ROLE_" + request.getRole().toUpperCase())));

        // Création d’un profil vide par défaut
        Profil profil = Profil.builder()
                .avatarUrl(null)
                .bio(null)
                .address(null)
                .utilisateur(user)
                .build();
        user.setProfil(profil);

        return utilisateurRepository.save(user);
    }

    /**
     * @param request 
     * @param ipAddress
     * @param device
     * @return
     */
    @Override
    public JwtResponse login(LoginRequest request, String ipAddress, String device) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String accessToken = jwtUtil.generateToken(utilisateur.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(utilisateur).getToken();

        connexionHistoryService.saveConnexion(utilisateur, ipAddress, device);

        return JwtResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .type("Bearer")
                .username(utilisateur.getUsername())
                .build();
    }


    /**
     * @param email 
     * @return
     */
    @Override
    public Utilisateur getUserByEmail(String email) {
        return utilisateurRepository.findByUsername(email).orElseThrow();
    }

    /**
     * @param email 
     * @return
     */
    @Override
    public String generateTokenFromEmail(String email) {
        return jwtUtil.generateToken(email);
    }

}
