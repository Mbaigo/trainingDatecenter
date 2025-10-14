package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;

import java.util.List;
import java.util.Optional;

public interface ProfilService {
    Profil createProfilForUser(Long userId, ProfilRequest request);
    Profil updateProfilForUser(Long userId, ProfilRequest request);
    public Optional<Profil> findByUtilisateurEmail(String email);
    public Optional<Profil> findByUtilisateurPhoneNumber(String phoneNumber);
    public List<Profil> findAllProfils();
}
