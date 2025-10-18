package com.mbaigo.trainingtools.training_tools.user.services.impl;

import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.security.services.JwtAuthenticationUtil;
import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.*;
import com.mbaigo.trainingtools.training_tools.user.mappers.ExperienceMapper;
import com.mbaigo.trainingtools.training_tools.user.mappers.SpecialityMapper;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ExperienceRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ProfilRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.SpecialityRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
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
    private  final SpecialityMapper specialityMapper;
    private  final ExperienceMapper experienceMapper;
    private final JwtAuthenticationUtil jwtAuthenticationUtil;

    @Override
    public Profil createProfilForUser(HttpServletRequest req, ProfilRequest request) {
        Utilisateur user = jwtAuthenticationUtil.getAuthenticatedUser(req);

        // ✅ Vérification du type : seuls les Trainers peuvent avoir un profil
        if (!(user instanceof Trainer trainer)) {
            throw new TrainingApiException("Seuls les utilisateurs de type TRAINER peuvent créer un profil.", 403);
        }

        // ✅ Vérification qu’un profil n’existe pas déjà
        if (trainer.getProfil() != null) {
            throw new TrainingApiException("Un profil existe déjà pour cet utilisateur.", 400);
        }

        // ✅ Construction du profil
        Profil profil = Profil.builder()
                .nom(user.getFirstName())
                .prenom(user.getLastName())
                .email(user.getEmail())
                .certification(request.getCertification())
                .avatarUrl(request.getAvatarUrl())
                .parcours(request.getParcours())
                .githubUrl(request.getGithubUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .build();

        // 🔹 Sauvegarde
        Profil savedProfil = profilRepository.save(profil);

        // 🔹 Association du profil au Trainer
        profil.setTrainer(trainer);
        trainer.setProfil(profil);

        utilisateurRepository.save(trainer);

        return savedProfil;
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
        return Optional.ofNullable(profilRepository.findByTrainerPhoneNumber(phoneNumber).orElseThrow(() -> new EntityNotFoundException("Profil non trouvé pour le numéro: " + phoneNumber)));
    }

    @Override
    public List<Profil> findAllProfils() {
        return profilRepository.findAll();
    }

    /**
     * @param profilId 
     * @param experienceRequest
     * @return
     */
    @Override
    public Experience addExperienceToProfil(Long profilId, ExperienceRequest experienceRequest) {
        Profil profil=profilRepository.findById(profilId).orElseThrow(()-> new EntityNotFoundException("Profil inexistant"));
        Experience experience = experienceMapper.toEntity(experienceRequest, profil);
        return experienceRepository.save(experience);
    }

    /**
     * @param profilId 
     * @param speciality
     * @return
     */
    @Override
    public Speciality addSpecialityToProfil(Long profilId, SpecialityRequest speciality) {
        Profil profil = profilRepository.findById(profilId)
                .orElseThrow(() -> new IllegalArgumentException("Profil non trouvé"));

        Speciality specialite = specialityMapper.toEntity(speciality, profil);
        return specialityRepository.save(specialite);
    }
}
