package com.mbaigo.trainingtools.training_tools.user.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ProfilRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilServiceImpl implements ProfilService {

    private final ProfilRepository profilRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Profil createProfilForUser(Long userId, ProfilRequest request) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Profil profil = Profil.builder()
                .avatarUrl(request.getAvatarUrl())
                .parcours(request.getParcours())
                .githubUrl(request.getGithubUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .utilisateur(user)
                .build();

        Profil saved = profilRepository.save(profil);
        user.setProfil(saved);
        utilisateurRepository.save(user);
        return saved;
    }

    /**
     * @param userId 
     * @param request
     * @return
     */
    @Override
    public Profil updateProfilForUser(Long userId, ProfilRequest request) {
        return null;
    }
}
