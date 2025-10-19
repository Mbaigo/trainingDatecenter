package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;

import java.util.List;
import java.util.Optional;

public interface ProfilService {
    Profil createProfilForUser(ProfilRequest request);
    Profil updateProfilForUser(Long userId, ProfilRequest request);
    public Optional<Profil> findByUtilisateurEmail(String email);
    public Optional<Profil> findByUtilisateurPhoneNumber(String phoneNumber);
    public List<Profil> findAllProfils();
    Experience addExperienceToProfil(Long profilId, ExperienceRequest experienceRequest);
    Speciality addSpecialityToProfil(Long profilId, SpecialityRequest speciality);
}
