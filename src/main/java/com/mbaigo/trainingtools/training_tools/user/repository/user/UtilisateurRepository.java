package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Learner;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // =============================
    // 🔍 RECHERCHES GÉNÉRIQUES
    // =============================
     Optional<Utilisateur> findByUsername(String username);
     Optional<Utilisateur> findByEmail(String email);
     boolean existsByUsername(String username);
     boolean existsByEmail(String email);


    Optional<Utilisateur> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    // =============================
    // 🔍 RECHERCHES SPÉCIFIQUES TRAINER
    // =============================

    /**
     * Recherche un Trainer par adresse mail.
     */
    @Query("SELECT t FROM Trainer t WHERE LOWER(t.email) = LOWER(:email)")
    Optional<Trainer> findTrainerByEmail(@Param("email") String email);

    /**
     * Recherche un Trainer par numéro de téléphone.
     */
    @Query("SELECT t FROM Trainer t WHERE t.phoneNumber = :phoneNumber")
    Optional<Trainer> findTrainerByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * Liste tous les Trainers.
     */
    @Query("SELECT t FROM Trainer t")
    List<Trainer> findAllTrainers();


    // =============================
    // 🔍 RECHERCHES SPÉCIFIQUES LEARNER
    // =============================

    /**
     * Recherche un Learner par adresse mail.
     */
    @Query("SELECT l FROM Learner l WHERE LOWER(l.email) = LOWER(:email)")
    Optional<Learner> findLearnerByEmail(@Param("email") String email);

    /**
     * Recherche un Learner par numéro de téléphone.
     */
    @Query("SELECT l FROM Learner l WHERE l.phoneNumber = :phoneNumber")
    Optional<Learner> findLearnerByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * Liste tous les Learners.
     */
    @Query("SELECT l FROM Learner l")
    List<Learner> findAllLearners();

    // =============================
    // 🔄 MISE À JOUR
    // =============================

    /**
     * Met à jour le nom, prénom et téléphone d’un utilisateur.
     * <p>
     * ⚠️ Utilise une requête JPQL avec @Modifying car c’est une requête d’écriture.
     * </p>
     */
    @Modifying
    @Query("""
        UPDATE Utilisateur u
        SET u.firstName = :firstName,
            u.lastName = :lastName,
            u.phoneNumber = :phoneNumber
        WHERE u.id = :id
    """)
    int updateUtilisateurInfo(@Param("id") Long id,
                              @Param("lastName") String lastName,
                              @Param("firstName") String firstName,
                              @Param("phoneNumber") String phoneNumber);
}
