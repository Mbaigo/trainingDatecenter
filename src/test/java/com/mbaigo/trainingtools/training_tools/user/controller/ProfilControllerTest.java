package com.mbaigo.trainingtools.training_tools.user.controller;

import com.mbaigo.trainingtools.training_tools.controller.model.ApiResponse;
import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilResponseDto;
import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;
import com.mbaigo.trainingtools.training_tools.user.services.ProfilService;
import com.mbaigo.trainingtools.training_tools.user.services.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfilControllerTest {

    @Mock
    private ProfilService profilService;

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private ProfilController profilController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProfil() {
        ProfilRequest request = new ProfilRequest();
        ProfilResponseDto responseDto = new ProfilResponseDto();

        when(profilService.createProfilForUser(request)).thenReturn(new Profil());

        ResponseEntity<ProfilResponseDto> response = profilController.createProfil(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(profilService, times(1)).createProfilForUser(request);
    }

    @Test
    void testUpdateProfil() {
        Long id = 1L;
        ProfilRequest request = new ProfilRequest();
        Profil profil = new Profil();

        when(profilService.updateProfilForUser(id, request)).thenReturn(profil);

        ResponseEntity<Profil> response = profilController.updateProfil(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profil, response.getBody());
        verify(profilService, times(1)).updateProfilForUser(id, request);
    }

    @Test
    void testSetExperienceToProfil() {
        ExperienceRequest request = new ExperienceRequest();
        Experience experience = new Experience();

        when(profilService.addExperienceToProfil(request)).thenReturn(experience);

        ResponseEntity<Experience> response = profilController.setExperienceToProfil(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(experience, response.getBody());
        verify(profilService, times(1)).addExperienceToProfil(request);
    }

    @Test
    void testSetSpecialityToProfil() {
        SpecialityRequest request = new SpecialityRequest();
        Speciality speciality = new Speciality();

        when(profilService.addSpecialityToProfil(request)).thenReturn(speciality);

        ResponseEntity<Speciality> response = profilController.setSpecialityToProfil(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(speciality, response.getBody());
        verify(profilService, times(1)).addSpecialityToProfil(request);
    }

    @Test
    void testGetAllProfils() {
        List<Profil> profils = List.of(new Profil());

        when(profilService.findAllProfils()).thenReturn(profils);

        ResponseEntity<ApiResponse<List<Profil>>> response = profilController.getAllProfils();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(profils, response.getBody().getData());
        verify(profilService, times(1)).findAllProfils();
    }

    @Test
    void testGetProfilByUtilisateurEmail() {
        String email = "test@example.com";
        Optional<Profil> profil = Optional.of(new Profil());

        when(profilService.findByUtilisateurEmail(email)).thenReturn(profil);

        ResponseEntity<ApiResponse<Optional<Profil>>> response = profilController.getProfilByUtilisateurEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(profil, response.getBody().getData());
        verify(profilService, times(1)).findByUtilisateurEmail(email);
    }

    @Test
    void testGetProfilByParcours() {
        String parcours = "Science";
        List<Profil> profils = List.of(new Profil());

        when(profilService.findProfilByParcours(parcours)).thenReturn(profils);

        ResponseEntity<ApiResponse<List<Profil>>> response = profilController.getProfilByParcours(parcours);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(profils, response.getBody().getData());
        verify(profilService, times(1)).findProfilByParcours(parcours);
    }

    @Test
    void testGetProfilByCertificate() {
        String certification = "Java";
        List<Profil> profils = List.of(new Profil());

        when(profilService.findProfilByCertification(certification)).thenReturn(profils);

        ResponseEntity<ApiResponse<List<Profil>>> response = profilController.getProfilByCertificate(certification);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(profils, response.getBody().getData());
        verify(profilService, times(1)).findProfilByCertification(certification);
    }
}
