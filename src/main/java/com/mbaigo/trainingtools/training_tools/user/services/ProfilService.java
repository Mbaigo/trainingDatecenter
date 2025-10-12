package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.auth.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;

public interface ProfilService {
    Profil createProfilForUser(Long userId, ProfilRequest request);
    Profil updateProfilForUser(Long userId, ProfilRequest request);
}
