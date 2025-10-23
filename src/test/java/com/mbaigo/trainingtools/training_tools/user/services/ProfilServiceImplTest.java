package com.mbaigo.trainingtools.training_tools.user.services;

import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ProfilRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.impl.ProfilServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfilServiceImplTest {

    @Mock
    private ProfilRepository profilRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private ProfilServiceImpl profilService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void testCreateProfilForUser_Success() {
        ProfilRequest request = new ProfilRequest();
        request.setCertification("Java");

        Trainer trainer = new Trainer();
        trainer.setEmail("trainer@example.com");

        when(authentication.getName()).thenReturn("trainer@example.com");
        when(utilisateurRepository.findByEmail("trainer@example.com")).thenReturn(Optional.of(trainer));
        when(profilRepository.save(any(Profil.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Profil result = profilService.createProfilForUser(request);

        assertNotNull(result);
        assertEquals(request.getCertification(), result.getCertification());
        verify(profilRepository, times(1)).save(any(Profil.class));
    }

    @Test
    void testCreateProfilForUser_TrainerNotFound() {
        when(authentication.getName()).thenReturn("unknown@example.com");
        when(utilisateurRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        ProfilRequest request = new ProfilRequest();

        TrainingApiException exception = assertThrows(TrainingApiException.class, () -> profilService.createProfilForUser(request));
        assertEquals("Utilisateur non trouvé avec l'email : unknown@example.com", exception.getMessage());
    }

    @Test
    void testUpdateProfilForUser_Success() {
        Long userId = 1L;
        ProfilRequest request = new ProfilRequest();
        request.setCertification("Updated Certification");

        Profil existingProfil = new Profil();
        existingProfil.setId(userId);

        when(profilRepository.findById(userId)).thenReturn(Optional.of(existingProfil));
        when(profilRepository.save(any(Profil.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Profil result = profilService.updateProfilForUser(userId, request);

        assertNotNull(result);
        assertEquals(request.getCertification(), result.getCertification());
        verify(profilRepository, times(1)).save(existingProfil);
    }

}
