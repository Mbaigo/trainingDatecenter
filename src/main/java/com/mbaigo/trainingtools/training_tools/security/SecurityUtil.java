package com.mbaigo.trainingtools.training_tools.security;

import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    public static Utilisateur getCurrentUser(UtilisateurRepository repo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) throw new TrainingApiException("Non authentifié", 401);
        Object principal = auth.getPrincipal();
        if (!(principal instanceof UserDetails userDetails))
            throw new TrainingApiException("Principal non valide", 403);
        return repo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new TrainingApiException("Utilisateur non trouvé", 404));
    }
}
