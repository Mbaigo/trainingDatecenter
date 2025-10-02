package com.mbaigo.trainingTools.training_tools.services.impl;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainerDto;
import com.mbaigo.trainingTools.training_tools.config.mapper.impl.TrainerMapper;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.TrainerRepository;
import com.mbaigo.trainingTools.training_tools.services.TrainerService;
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
    public TrainerDto save(TrainerDto trainer) {
        return mapper.toDto(repository.save(mapper.toEntity(trainer)));
    }

    /**
     * @return 
     */
    @Override
    public List<TrainerDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Optional<TrainerDto> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).map(mapper::toDto).orElseThrow(() -> new EntityNotFoundException("Trainer inexistant " + id)));
    }

    /**
     * @param firstName 
     * @return
     */
    @Override
    public List<TrainerDto> findByFirstNameIgnoreCase(String firstName) {
        return repository.findByFirstNameIgnoreCase(firstName.trim()).stream().map(mapper::toDto).toList();
    }

    /**
     * @param lastName 
     * @return
     */
    @Override
    public List<TrainerDto> findByLastNameIgnoreCase(String lastName) {
        return repository.findByLastNameIgnoreCase(lastName.trim()).stream().map(mapper::toDto).toList();
    }

    /**
     * @param phoneNumber
     * @return
     */
    @Override
    public Optional<TrainerDto> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(repository.findByPhoneNumber(phoneNumber).map(mapper::toDto).orElseThrow(() -> new EntityNotFoundException("Trainer inexistant " + phoneNumber)));
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Optional<TrainerDto> findByMailAdress(String email) {
        return Optional.ofNullable(repository.findByMailAdress(email).map(mapper::toDto).orElseThrow(() -> new EntityNotFoundException("Trainer inexistant " + email)));
    }
}
