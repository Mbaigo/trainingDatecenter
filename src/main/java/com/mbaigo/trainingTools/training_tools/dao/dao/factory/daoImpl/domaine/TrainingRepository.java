package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.domaine;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    // Derived query: find by status with sorting
    List<Training> findAllByStatusOrderByCreatedAtDesc(Boolean status);
    List<Training> findByTitle(String title);



}
