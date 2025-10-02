package com.mbaigo.trainingTools.training_tools.services;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainerDto;

import java.util.List;
import java.util.Optional;

public interface TrainerService {
    public TrainerDto save(TrainerDto trainer);
    public List<TrainerDto> findAll();
    public Optional<TrainerDto> findById(Long id);
    public List<TrainerDto> findByFirstNameIgnoreCase(String firstName);
    public List<TrainerDto> findByLastNameIgnoreCase(String lastName);
    public Optional<TrainerDto> findByPhoneNumber(String phoneNumber);
    public Optional<TrainerDto> findByMailAdress(String email);
}
