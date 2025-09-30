package com.mbaigo.trainingTools.training_tools.services;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.TrainerRepository;
import com.mbaigo.trainingTools.training_tools.enumeration.MatchMode;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service @Transactional
public class TrainerServiceImpl implements TrainerService{
    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Optional<Trainer> findByFirstNameIgnoreCase(String firstName) {
        return trainerRepository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public Optional<Trainer> findByLastNameIgnoreCase(String lastName) {
        return trainerRepository.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public Optional<Trainer> findByPhoneNumber(String phoneNumber) {
        return trainerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Trainer> findByMailAdress(String mailAdress) {
        return trainerRepository.findByMailAdress(mailAdress);
    }

    @Override
    public List<Trainer> findByEmailContaining(String emailFragment) {
        return trainerRepository.findByEmailContaining(emailFragment);
    }

    @Override
    public List<Trainer> searchByFullName(String firstName, String lastName, MatchMode mode) {
        // Gère les cas nulls
        if (firstName == null) firstName = "";
        if (lastName == null) lastName = "";
        if (mode == MatchMode.EXACT) {
            return trainerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
        } else {
            return trainerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
        }
    }

    @Override
    public Trainer create(Trainer entity) {
        return trainerRepository.save(entity);
    }

    @Override
    public Trainer update(Trainer entity) {
        return trainerRepository.save(entity);
    }

    @Override
    public Optional<Trainer> findById(Long aLong) {
        return trainerRepository.findById(aLong);
    }

    @Override
    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public List<Trainer> findAllBySorted(String sortProperty, boolean ascending) {
        return trainerRepository.findAll();
    }

    @Override
    public void delete(Trainer entity) {
        trainerRepository.delete(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        trainerRepository.deleteById(aLong);
    }

    @Override
    public long count() {
        return trainerRepository.count();
    }
}
