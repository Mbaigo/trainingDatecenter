package com.mbaigo.trainingTools.training_tools.services;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Trainer;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainerDto;

import java.util.List;
import java.util.Optional;

public interface TrainerService {
    public Trainer save(Trainer trainer);
    public List<Trainer> findAll();
    public Optional<Trainer> findById(Long id);
    public List<Trainer> findByFirstNameIgnoreCase(String firstName);
    public List<Trainer> findByLastNameIgnoreCase(String lastName);
    public Optional<Trainer> findByPhoneNumber(String phoneNumber);
    public Optional<Trainer> findByMailAdress(String email);
}
