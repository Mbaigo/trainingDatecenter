package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl;

import com.mbaigo.trainingTools.training_tools.beans.Training;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.GenerikRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    // Derived query: find by status with sorting
    List<Training> findAllByStatusOrderByCreatedAtDesc(Boolean status);
    List<Training> findByTitle(String title);



}
