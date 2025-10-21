package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<Profil , Long> {
     Optional<Profil> findByTrainer(Trainer utilisateur);
     Optional<Profil> findByEmail(String email);
     Optional<Profil> findByTrainerPhoneNumber(String phoneNumber);
     List<Profil> findByCertificationContaining(String certification);
     List<Profil> findByParcoursContaining(String parcours);

}
