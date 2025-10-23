package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ProfilRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.impl.TrainerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private ProfilRepository profilRepository;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Trainer> trainers = List.of(new Trainer());

        when(utilisateurRepository.findAllTrainers()).thenReturn(trainers);

        List<Trainer> result = trainerService.findAll();

        assertNotNull(result);
        assertEquals(trainers.size(), result.size());
        verify(utilisateurRepository, times(1)).findAllTrainers();
    }

    @Test
    void testFindByFirstNameIgnoreCase() {
        String firstName = "John";

        List<Trainer> result = trainerService.findByFirstNameIgnoreCase(firstName);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByLastNameIgnoreCase() {
        String lastName = "Doe";

        List<Trainer> result = trainerService.findByLastNameIgnoreCase(lastName);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByPhoneNumber() {
        String phoneNumber = "1234567890";
        Trainer trainer = new Trainer();

        when(utilisateurRepository.findTrainerByPhoneNumber(phoneNumber)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerService.findByPhoneNumber(phoneNumber);

        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
        verify(utilisateurRepository, times(1)).findTrainerByPhoneNumber(phoneNumber);
    }
}
