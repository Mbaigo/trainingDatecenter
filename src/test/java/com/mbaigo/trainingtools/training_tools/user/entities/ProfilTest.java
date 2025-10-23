package com.mbaigo.trainingtools.training_tools.user.entities;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfilTest {

    @Test
    void testProfilConstructorAndGetters() {
        Profil profil = new Profil();
        profil.setNom("Doe");
        profil.setPrenom("John");
        profil.setEmail("john.doe@example.com");

        assertEquals("Doe", profil.getNom());
        assertEquals("John", profil.getPrenom());
        assertEquals("john.doe@example.com", profil.getEmail());
    }

    @Test
    void testProfilEqualsAndHashCode() {
        Profil profil1 = new Profil();
        profil1.setId(1L);

        Profil profil2 = new Profil();
        profil2.setId(1L);

        assertEquals(profil1, profil2);
        assertEquals(profil1.hashCode(), profil2.hashCode());
    }
}
