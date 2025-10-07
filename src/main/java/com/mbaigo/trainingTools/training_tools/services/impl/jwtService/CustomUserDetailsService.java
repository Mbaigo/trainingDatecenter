package com.mbaigo.trainingTools.training_tools.services.impl.jwtService;

import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.domaine.userRole.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository userRepository;

    public CustomUserDetailsService(UtilisateurRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));
    }
}

