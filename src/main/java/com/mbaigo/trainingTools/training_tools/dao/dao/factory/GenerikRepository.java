package com.mbaigo.trainingTools.training_tools.dao.dao.factory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

@NoRepositoryBean
public interface GenerikRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    // ---------- Opérations avancées ----------

    /**
     * Recherche toutes les entités avec tri simple (via derived query).
     * Ex: repository.findAllBySortProperty("name", true)
     * Correspond à trier par une propriété donnée.
     */
    List<T> findAllBySortProperty(String sortProperty, boolean ascending);

    /**
     * Recherche avec pagination & tri plus facilement via Spring Data Pageable.
     * Utilise un Pageable pour définir page, taille et tri.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Recherche avec critères dynamiques via Specifications (si utilisée).
     * Ex: repository.findAll(Specification<T> spec)
     * On conserve ici une méthode utilitaire qui peut être utilisée avec une Specification externe.
     */
    List<T> findAllBySpecification(org.springframework.data.jpa.domain.Specification<T> spec);

    /**
     * Projection légère: retourne des résultats transformés via une Function.
     * Exemple: convertir T en DTO ou interface projection.
     */
    <R> List<R> findAllProjected(Function<T, R> projectionFunction);

    /**
     * Pagination + projection légère via une Function.
     */
    <R> List<R> findAllWithProjection(int pageIndex, int pageSize,
                                      Function<T, R> projectionFunction);

    // ---------- Mise à jour partielle ----------

    /**
     * Mise à jour partielle: mettre à jour uniquement les champs non-null.
     * Implémentation possible via @Query UPDATE ou via merge partiel spécifique.
     */
    @Modifying
    @Query("update #{#entityName} e set /* champs dynamiques */ where e.id = :id")
    int updatePartial(ID id, T partialEntity);
}
