package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.GenerikRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends GenerikRepository<Trainer, Long>  {
    // Recherches utiles sur Auteur
    Optional<Trainer> findByFirstNameIgnoreCase(String nom);
    Optional<Trainer> findByLastNameIgnoreCase(String nom);
    Optional<Trainer> findByPhoneNumber(String nom);
    Optional<Trainer> findByMailAdress(String email);

    // Recherche exact sur prénom et nom, ignore case
    List<Trainer> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    // Partial match sur les deux champs
    List<Trainer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

    // Projection: DTO via interface projection
    //List<UserSummary> findAllProjectedByStatus(String status);

    // JPQL personnalisé
    @Query("select t from Trainer t where t.email like %:mail%")
    List<Trainer> findByEmailContaining(@Param("mail") String mail);
}
