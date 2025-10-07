package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.domaine;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    // Recherches utiles sur Auteur
    List<Trainer> findByFirstNameIgnoreCase(String nom);
    List<Trainer> findByLastNameIgnoreCase(String nom);
    Optional<Trainer> findByPhoneNumber(String nom);
    Optional<Trainer> findByMailAdress(String email);

    // Recherche exact sur prénom et nom, ignore case
    List<Trainer> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    // Partial match sur les deux champs
    List<Trainer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

    // Projection: DTO via interface projection
    //List<UserSummary> findAllProjectedByStatus(String status);

    // JPQL personnalisé
    @Query("select t from Trainer t where t.mailAdress like %:mail%")
    List<Trainer> findByEmailContaining(@Param("mailAdress") String mail);
}
