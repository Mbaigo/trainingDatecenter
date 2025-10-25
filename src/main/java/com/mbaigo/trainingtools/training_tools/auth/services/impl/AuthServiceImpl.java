package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.dto.*;
import com.mbaigo.trainingtools.training_tools.auth.services.AuthService;
import com.mbaigo.trainingtools.training_tools.auth.services.ConnexionHistoryService;
import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.auth.token.services.RefreshTokenService;
import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.security.SecurityUtil;
import com.mbaigo.trainingtools.training_tools.security.services.jwt_service.JwtUtil;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.admin.Admin;
import com.mbaigo.trainingtools.training_tools.user.entities.users.*;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final ConnexionHistoryService connexionHistoryService;
    private final ProfilService profilService;
    public JwtResponse register(RegisterFirstRequest request, HttpServletRequest httpRequest) {
        // ✅ Vérification de la correspondance des mots de passe
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new TrainingApiException("Les mots de passe ne correspondent pas.", 400);
        }

        // ✅ Vérification si l’email existe déjà
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new TrainingApiException("Un utilisateur avec cet email existe déjà.", 400);
        }

        // ✅ Création dynamique selon le rôle
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TrainingApiException("Rôle invalide. Valeurs possibles : ADMIN, TRAINER, LEARNER.", 400);
        }

        Utilisateur user = UtilisateurFactory.createUser(role);

        // ✅ Affectation des attributs communs
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setActif(true);
        user.setDateInscription(LocalDateTime.now());
        user.setRoles(Set.of(role));

        utilisateurRepository.save(user);

        // ✅ Auto Login après enregistrement
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(request.getEmail());
        loginRequest.setPassword(request.getPassword());

        String ipAddress = httpRequest.getRemoteAddr();
        String device = httpRequest.getHeader("User-Agent");

        return login(loginRequest, ipAddress, device);
    }


    @Override
    public Utilisateur updateDetails(UpdateUserDetailsRequest request) {
        Utilisateur currentUser = SecurityUtil.getCurrentUser(utilisateurRepository);
        // Vérification : currentUser est admin OU c'est bien le même utilisateur
        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(role -> role == Role.ADMIN);

        if (!isAdmin && Objects.isNull(currentUser.getId())) {
            throw new TrainingApiException("Accès refusé : vous ne pouvez modifier que votre propre profil.", 403);
        }

        if (request.getUsername() != null)
            currentUser.setUsername(request.getUsername());
        currentUser.setFirstName(request.getFirstName());
        currentUser.setLastName(request.getLastName());
        currentUser.setPhoneNumber(request.getPhoneNumber());

        currentUser.setEnabled(true);
        return utilisateurRepository.save(currentUser);
    }


    /**
     * @param request 
     * @param ipAddress
     * @param device
     * @return
     */
    @Override
    public JwtResponse login(LoginRequest request, String ipAddress, String device) {
        log.info("Login attempt for email={}", request.getEmail());
        log.info("AuthenticationManager class = {}", authenticationManager.getClass().getName());
        if (authenticationManager instanceof ProviderManager pm) {
            log.info("ProviderManager providers = {}", pm.getProviders());
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        log.info("Calling authenticationManager.authenticate(...)");
        Authentication authentication = authenticationManager.authenticate(token);
        log.info("authentication completed: principalClass={}",
                authentication != null && authentication.getPrincipal() != null ? authentication.getPrincipal().getClass().getName() : "null");

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
    public void logout() {
        Utilisateur currentUser = SecurityUtil.getCurrentUser(utilisateurRepository);
        refreshTokenService.deleteByUtilisateurId(currentUser.getId());
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
