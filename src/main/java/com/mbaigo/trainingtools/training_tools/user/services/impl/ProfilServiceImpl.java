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
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Profil createProfilForUser(ProfilRequest request) {
        // 🔹 Récupère l'utilisateur connecté via le SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Utilisateur user = utilisateurRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new TrainingApiException("Utilisateur non trouvé avec l'email : " + auth.getName(), 404));


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
        return Profil.builder()
                .certification(savedProfil.getCertification())
                .avatarUrl(savedProfil.getAvatarUrl())
                .parcours(savedProfil.getParcours())
                .githubUrl(savedProfil.getGithubUrl())
                .linkedinUrl(savedProfil.getLinkedinUrl())
                .build();

    }


    /**
     * @param userId 
     * @param request
     * @return
     */
    @Override
    public Profil updateProfilForUser(Long userId, ProfilRequest request) {

        Profil profil = profilRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Profil non trouvé"));


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

     * @param experienceRequest
     * @return
     */
    @Override
    public Experience addExperienceToProfil(ExperienceRequest experienceRequest) {
        Experience experience = experienceMapper.toEntity(experienceRequest, getCurrentUserProfil());
        return experienceRepository.save(experience);
    }

    /**
     *
     * @param specialityRequest
     * @return
     */
    @Override
    public Speciality addSpecialityToProfil(SpecialityRequest specialityRequest) {
        // Créer la spécialité en utilisant le profil de l'utilisateur courant
        Speciality speciality = specialityMapper.toEntity(specialityRequest, getCurrentUserProfil());
        return  specialityRepository.save(speciality);
    }

    /**
     * @param certification 
     * @return
     */
    @Override
    public List<Profil> findProfilByCertification(String certification) {
        return profilRepository.findByCertificationContaining(certification.toUpperCase());
    }

    /**
     * @param specialityName 
     * @return
     */


    /**
     * @param parcours
     * @return
     */
    @Override
    public List<Profil> findProfilByParcours(String parcours) {
        return profilRepository.findByParcoursContaining(parcours);
    }


    private Profil getCurrentUserProfil() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        Utilisateur user = utilisateurRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'email : " + userEmail));

        if (!(user instanceof Trainer trainer)) {
            throw new TrainingApiException("Seuls les utilisateurs de type TRAINER ont un profil.", 403);
        }

        if (trainer.getProfil() == null) {
            throw new EntityNotFoundException("Profil non trouvé pour l'utilisateur : " + userEmail);
        }

        return trainer.getProfil();
    }
}
