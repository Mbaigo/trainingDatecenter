package com.mbaigo.trainingtools.training_tools.user.repository.learning;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Long> {
    Optional<Domaine> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
