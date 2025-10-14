package com.mbaigo.trainingtools.training_tools.user.services.impl;

import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ExperienceRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ProfilRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.SpecialityRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilServiceImpl implements ProfilService {

    private final ProfilRepository profilRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ExperienceRepository experienceRepository;
    private final SpecialityRepository specialityRepository;

    @Override
    public Profil createProfilForUser(Long userId, ProfilRequest request) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Profil profil = Profil.builder()
                .nom(user.getFirstName())
                .prenom(user.getLastName())
                .email(user.getEmail())
                .certification(request.getCertification())
                .avatarUrl(request.getAvatarUrl())
                .parcours(request.getParcours())
                .githubUrl(request.getGithubUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .utilisateur(user)
                .build();

        // Sauvegarde des sous-éléments si présents
        if (profil.getExperiences() != null) {
            profil.getExperiences().forEach(exp -> exp.setProfil(profil));
        }
        if (profil.getSpecialities() != null) {
            profil.getSpecialities().forEach(spec -> spec.setProfil(profil));
        }

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

        Profil profil = profilRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Profil non trouvé"));

        // Synchronisation automatique
        if (request.getNom() != null) profil.setNom(request.getNom());
        if (request.getPrenom() != null) profil.setPrenom(request.getPrenom());
        if (request.getEmail()!= null) profil.setEmail(request.getEmail());
        if (request.getCertification()!= null) profil.setCertification(request.getCertification());

        // Mise à jour des champs modifiables
        if (request.getPhotoUrl() != null) profil.setPhotoUrl(request.getPhotoUrl());
        if (request.getParcours() != null) profil.setParcours(request.getParcours());
        if (request.getLinkedinUrl() != null) profil.setLinkedinUrl(request.getLinkedinUrl());
        if (request.getGithubUrl() != null) profil.setGithubUrl(request.getGithubUrl());

        // gestion des listes associées
        if (profil.getExperiences() != null) {
            experienceRepository.deleteAll(profil.getExperiences());
            profil.getExperiences().forEach(exp -> exp.setProfil(profil));
            profil.setExperiences(profil.getExperiences());
        }

        if (profil.getSpecialities() != null) {
            specialityRepository.deleteAll(profil.getSpecialities());
            profil.getSpecialities().forEach(spec -> spec.setProfil(profil));
            profil.setSpecialities(profil.getSpecialities());
        }

        return profilRepository.save(profil);
    }

    /**
     * @param email 
     * @return
     */
    @Override
    public Optional<Profil> findByUtilisateurEmail(String email) {
        return Optional.ofNullable(profilRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Profil non trouvé pour l'email: " + email)));
    }

    /**
     * @param phoneNumber 
     * @return
     */
    @Override
    public Optional<Profil> findByUtilisateurPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(profilRepository.findByUtilisateurPhoneNumber(phoneNumber).orElseThrow(() -> new EntityNotFoundException("Profil non trouvé pour le numéro: " + phoneNumber)));
    }

    @Override
    public List<Profil> findAllProfils() {
        return profilRepository.findAll();
    }
}
