package com.mbaigo.trainingtools.training_tools.services.impl;

import com.mbaigo.trainingtools.training_tools.domain.Trainer;
import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.mapper.impl.TrainerMapper;
import com.mbaigo.trainingtools.training_tools.repository.TrainerRepository;
import com.mbaigo.trainingtools.training_tools.services.TrainerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service @Slf4j
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository repository;
    private final TrainerMapper mapper;

    public TrainerServiceImpl(TrainerRepository repository, TrainerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * @param trainer 
     * @return
     */
    @Override
    public Trainer save(Trainer trainer) {
        return repository.save(trainer);
    }

    /**
     * @return 
     */
    @Override
    public List<Trainer> findAll() {
        return repository.findAll();
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Optional<Trainer> findById(Long id) {
        return Optional.ofNullable(repository
                .findById(id)
                .orElseThrow(() -> new TrainingApiException("Trainer not found by id: " + id, 404, "TrainerService.findById")));
    }

    /**
     * @param firstName 
     * @return
     */
    @Override
    public List<Trainer> findByFirstNameIgnoreCase(String firstName) {
        return repository
                .findByFirstNameIgnoreCase(firstName.trim());
    }

    /**
     * @param lastName 
     * @return
     */
    @Override
    public List<Trainer> findByLastNameIgnoreCase(String lastName) {
        return repository
                .findByLastNameIgnoreCase(lastName.trim());
    }

    /**
     * @param phoneNumber
     * @return
     */
    @Override
    public Optional<Trainer> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new TrainingApiException("Trainer not found by phoneNumber : " + phoneNumber, 404, "TrainerService.findByPhoneNumber")));
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Optional<Trainer> findByMailAdress(String email) {
        return Optional.ofNullable(repository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Trainer inexistant " + email)));
    }
}
