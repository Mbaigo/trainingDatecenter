package com.mbaigo.trainingtools.training_tools.repository;

import com.mbaigo.trainingtools.training_tools.domain.Trainer;
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
    Optional<Trainer> findByEmail(String email);

    // Recherche exact sur prénom et nom, ignore case
    List<Trainer> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    // Partial match sur les deux champs
    List<Trainer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

    // Projection: DTO via interface projection
    //List<UserSummary> findAllProjectedByStatus(String status);

    // JPQL personnalisé
    @Query("select t from Trainer t where t.email like %:mail%")
    List<Trainer> findByEmailContaining(@Param("email") String mail);
}
