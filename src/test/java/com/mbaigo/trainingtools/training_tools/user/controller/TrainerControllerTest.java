package com.mbaigo.trainingtools.training_tools.user.controller;

import com.mbaigo.trainingtools.training_tools.controller.model.ApiResponse;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.services.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerControllerTest {

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrainers_Success() {
        List<Trainer> trainers = List.of(new Trainer());

        when(trainerService.findAll()).thenReturn(trainers);

        ResponseEntity<ApiResponse<List<Trainer>>> response = trainerController.getAllTrainers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(trainers, response.getBody().getData());
        verify(trainerService, times(1)).findAll();
    }

    @Test
    void testGetAllTrainers_NoContent() {
        when(trainerService.findAll()).thenReturn(List.of());

        ResponseEntity<ApiResponse<List<Trainer>>> response = trainerController.getAllTrainers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getData());
        verify(trainerService, times(1)).findAll();
    }
}
