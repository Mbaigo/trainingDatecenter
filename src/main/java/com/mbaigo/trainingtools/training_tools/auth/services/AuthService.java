package com.mbaigo.trainingtools.training_tools.auth.services;

import com.mbaigo.trainingtools.training_tools.auth.dto.*;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;

public interface AuthService {
    Utilisateur register(RegisterFirstRequest request);

    JwtResponse login(LoginRequest request, String ipAddress, String device);

    Utilisateur getUserByEmail(String email);
    Utilisateur updateDetails(Long userId, UpdateUserDetailsRequest request);
    Profil createProfile(Long userId, ProfilRequest request);

    String generateTokenFromEmail(String email);
    //refresh / logout
    JwtResponse refreshToken(String requestRefreshToken);
    void logout(Long userId);
}
