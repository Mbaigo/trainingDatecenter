package com.mbaigo.trainingtools.training_tools.auth.services;

import com.mbaigo.trainingtools.training_tools.auth.dto.JwtResponse;
import com.mbaigo.trainingtools.training_tools.auth.dto.LoginRequest;
import com.mbaigo.trainingtools.training_tools.auth.dto.RegisterRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;

public interface AuthService {
    Utilisateur register(RegisterRequest request);

    JwtResponse login(LoginRequest request, String ipAddress, String device);

    Utilisateur getUserByEmail(String email);

    String generateTokenFromEmail(String email);
}
