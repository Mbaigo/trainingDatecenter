package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.user.entities.admin.Admin;

/**
 * Factory responsable de la création des instances de {@link Utilisateur}
 * selon le rôle métier.
 * <p>
 * Cette approche permet de centraliser la logique d’instanciation
 * et d’éviter les switch/case répétitifs dans les services.
 * </p>
 *
 * Exemple d’utilisation :
 * <pre>
 * Role role = Role.TRAINER;
 * Utilisateur user = UtilisateurFactory.createUser(role);
 * </pre>
 *
 */
public final class UtilisateurFactory {

    // Empêche l’instanciation
    private UtilisateurFactory() {}

    /**
     * Crée dynamiquement une instance d’un sous-type de {@link Utilisateur}
     * selon le rôle spécifié.
     *
     * @param role le rôle métier (TRAINER, LEARNER, ADMIN)
     * @return une instance concrète de {@link Trainer}, {@link Learner} ou {@link Admin}
     * @throws TrainingApiException si le rôle est nul ou non supporté
     */
    public static Utilisateur createUser(Role role) {
        if (role == null) {
            throw new TrainingApiException("Le rôle ne peut pas être nul.", 400);
        }

        return switch (role) {
            case TRAINER -> new Trainer();
            case LEARNER -> new Learner();
            case ADMIN -> new Admin();
        };
    }
}
