package com.mbaigo.trainingTools.training_tools.services;

import com.mbaigo.trainingTools.training_tools.beans.Training;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainingDto;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.TrainingRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service @Transactional
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository repository;

    public TrainingServiceImpl(TrainingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Training create(Training training) {
        return repository.save(training);
    }

    @Override
    public Training update(Training training) {
        return repository.save(training);
    }

    @Override
    public Optional<Training> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Training> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Training publish(Long id, LocalDateTime publicationDate) {
        return null;
    }

    @Override
    public List<Training> searchByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<TrainingDto> findAllProjectedSummary() {
        return List.of();
    }

    @Override
    public List<Training> findAllBySpecification(Specification<Training> spec) {
        return List.of();
    }
}
