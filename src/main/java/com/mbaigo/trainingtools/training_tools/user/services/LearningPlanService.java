package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.LearningPlan;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.LearningPlanRequestDTO;

public interface LearningPlanService {
    public LearningPlan createLearningPlanForCurrentLearner(LearningPlanRequestDTO request);
}
