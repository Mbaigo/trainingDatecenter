package com.mbaigo.trainingtools.training_tools.user.repository.learning;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.LearningPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPlanRepository extends JpaRepository<LearningPlan, Long> {
}
