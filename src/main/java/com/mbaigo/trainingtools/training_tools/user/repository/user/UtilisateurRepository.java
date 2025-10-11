package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    public Optional<Utilisateur> findByUsername(String username);
    public Optional<Utilisateur> findByEmail(String email);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
