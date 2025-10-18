package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {
     List<Trainer> findAll();
     List<Trainer> findByFirstNameIgnoreCase(String firstName);
     List<Trainer> findByLastNameIgnoreCase(String lastName);
     Optional<Trainer> findByPhoneNumber(String phoneNumber);
     Optional<Trainer> findByMailAdress(String email);
}
