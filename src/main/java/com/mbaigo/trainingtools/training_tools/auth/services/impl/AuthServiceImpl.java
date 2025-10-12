package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.dto.*;
import com.mbaigo.trainingtools.training_tools.auth.services.AuthService;
import com.mbaigo.trainingtools.training_tools.auth.services.ConnexionHistoryService;
import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.auth.token.services.RefreshTokenService;
import com.mbaigo.trainingtools.training_tools.security.services.jwt_service.JwtUtil;
import com.mbaigo.trainingtools.training_tools.user.entities.admin.Admin;
import com.mbaigo.trainingtools.training_tools.user.entities.users.*;
import com.mbaigo.trainingtools.training_tools.user.repository.user.RoleRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
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
    private final RoleRepository roleRepository;
    private final ProfilService profilService;

    public Utilisateur register(RegisterFirstRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        String roleName = request.getRole().toUpperCase();
        Utilisateur user;
        switch (roleName) {
            case "TRAINER" -> user = new Trainer();
            case "LEARNER" -> user = new Learner();
            case "ADMIN" -> user = new Admin();
            default -> throw new IllegalArgumentException("Rôle invalide");
        }

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(false);
        user.setActif(true);

        Role role = roleRepository.findByName("ROLE_" + roleName)
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_" + roleName)));

        user.setRoles(Set.of(role));
        return utilisateurRepository.save(user);
    }

    @Override
    public Utilisateur updateDetails(Long userId, UpdateUserDetailsRequest request) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if (request.getUsername() != null) user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLevel(request.getLevel());

        user.setEnabled(true);
        return utilisateurRepository.save(user);
    }

    @Override
    public Profil createProfile(Long userId, ProfilRequest request) {
        Profil profil = profilService.createProfilForUser(userId, request);
        // ensure user enabled
        Utilisateur user = utilisateurRepository.findById(userId).orElseThrow();
        if (!user.isEnabled()) {
            user.setEnabled(true);
            utilisateurRepository.save(user);
        }
        return profil;
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
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable by email : " + request.getEmail()));

        if (!utilisateur.isEnabled() || !utilisateur.isActif()) {
            throw new IllegalStateException("Compte inactif");
        }

        String accessToken = jwtUtil.generateToken(utilisateur.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(utilisateur);

        connexionHistoryService.saveConnexion(utilisateur, ipAddress, device);

        return JwtResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken.getToken())
                .type("Bearer")
                .username(utilisateur.getUsername())
                .build();
    }


    @Override
    public JwtResponse refreshToken(String requestRefreshToken) {
        RefreshToken stored = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token introuvable"));
        refreshTokenService.verifyExpiration(stored);
        Utilisateur user = stored.getUtilisateur();
        String newAccessToken = jwtUtil.generateToken(user.getEmail());
        return JwtResponse.builder()
                .token(newAccessToken)
                .refreshToken(stored.getToken())
                .type("Bearer")
                .build();
    }

    @Override
    public void logout(Long userId) {
        refreshTokenService.deleteByUtilisateurId(userId);
        SecurityContextHolder.clearContext();
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
