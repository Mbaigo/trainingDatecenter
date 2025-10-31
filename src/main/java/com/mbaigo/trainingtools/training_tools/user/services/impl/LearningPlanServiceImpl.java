package com.mbaigo.trainingtools.training_tools.user.services.impl;

import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.Domaine;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.LearningPlan;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.LearningPlanRequestDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.learnerplanning.dto.LearningPlanResponseDTO;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Learner;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Skill;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.mappers.LearningPlanMapper;
import com.mbaigo.trainingtools.training_tools.user.mappers.SkillMapper;
import com.mbaigo.trainingtools.training_tools.user.repository.learning.DomaineRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.learning.LearningPlanRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.LearningPlanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LearningPlanServiceImpl implements LearningPlanService {

    private final DomaineRepository domaineRepository;
    private final LearningPlanRepository learningPlanRepository;
    private final UtilisateurRepository utilisateurRepository;
    @Override
    public LearningPlan createLearningPlanForCurrentLearner(LearningPlanRequestDTO request) {
        // 🔹 1. Récupérer le learner connecté
        Utilisateur currentUser = getCurrentUser(); // (implémentée via SecurityContext)
        if (!(currentUser instanceof Learner learner)) {
            throw new TrainingApiException("Seuls les Learners peuvent créer un plan d'apprentissage.", 403);
        }

        // 🔹 2. Trouver le domaine
        Domaine domaine = domaineRepository.findByNameIgnoreCase(request.getDomainName())
                .orElseThrow(() -> new TrainingApiException("Domaine introuvable : " + request.getDomainName(), 404));

        // 🔹 3. Créer et mapper le plan
        LearningPlan plan = LearningPlanMapper.toEntity(request, domaine);
        plan.setLearner(learner);

        return learningPlanRepository.save(plan);
    }

    @Transactional
    public LearningPlanResponseDTO updateLearningPlan(Long planId, LearningPlanRequestDTO dto) {
        Utilisateur currentUser = getCurrentUser();
        if (!(currentUser instanceof Learner learner)) {
            throw new TrainingApiException("Seuls les Learners peuvent créer un plan d'apprentissage.", 403);
        }

        LearningPlan plan = learningPlanRepository.findById(planId)
                .orElseThrow(() -> new TrainingApiException("Learning plan not found",402));

        if (!plan.getLearner().getId().equals(learner.getId())) {
            throw new TrainingApiException("You can only edit your own learning plan",303);
        }

        // 🔹 Mettre à jour les champs du plan
        plan.setGoal(dto.getGoal());
        plan.setDurationInMonths(dto.getDurationInMonths());
        plan.setHoursPerWeek(dto.getHoursPerWeek());
        plan.setCurrentLevel(dto.getCurrentLevel());
        plan.setTargetLevel(dto.getTargetLevel());
        plan.setPriority(dto.getPriority());

        // 🔹 Mettre à jour le domaine (si changé)
        if (dto.getDomainName() != null && !dto.getDomainName().isBlank()) {
            Domaine domaine = domaineRepository.findByNameIgnoreCase(dto.getDomainName())
                    .orElseThrow(() -> new TrainingApiException("Domain not found: " + dto.getDomainName(), 404));
            plan.setDomaine(domaine);
        }

        // 🔹 Mettre à jour les compétences (optionnel)
        if (dto.getSkills() != null && !dto.getSkills().isEmpty()) {
            plan.getSkills().clear();
            dto.getSkills().forEach(skillDTO -> {
                Skill skill = SkillMapper.toEntity(skillDTO);
                skill.setLearningPlan(plan);
                plan.getSkills().add(skill);
            });
        }

        plan.setUpdatedAt(LocalDateTime.now());
        LearningPlan updated = learningPlanRepository.save(plan);

        return LearningPlanMapper.toDTO(updated);
    }

    private Utilisateur getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserDetails userDetails)) {
            throw new TrainingApiException("Utilisateur non authentifié", 401);
        }
        return utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new TrainingApiException("Utilisateur non trouvé", 404));
    }
}

