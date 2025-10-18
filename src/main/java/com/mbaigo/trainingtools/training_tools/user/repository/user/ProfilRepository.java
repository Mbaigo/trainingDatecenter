package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<Profil , Long> {
    public Optional<Profil> findByTrainer(Trainer utilisateur);
    public Optional<Profil> findByEmail(String email);
    public Optional<Profil> findByTrainerPhoneNumber(String phoneNumber);
}
