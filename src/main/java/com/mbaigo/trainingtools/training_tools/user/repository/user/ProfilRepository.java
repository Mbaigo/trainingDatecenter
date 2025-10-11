package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil , Long> {
    public Profil findByUtilisateur(Utilisateur utilisateur);
    public Profil findByUtilisateurEmail(String email);
    public Profil findByUtilisateurPhoneNumber(String phoneNumber);
}
