package com.mbaigo.trainingtools.training_tools.repository;

import com.mbaigo.trainingtools.training_tools.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    // Derived query: find by status with sorting
    List<Training> findAllByStatusOrderByCreatedAtDesc(Boolean status);
    List<Training> findByTitle(String title);



}
