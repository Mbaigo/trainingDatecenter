package com.mbaigo.trainingtools.training_tools.user.entities;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityTest {
    @Test
    void testSpecialityConstructorAndGetters() {
        Speciality speciality = new Speciality();
        speciality.setName("Java Development");

        assertEquals("Java Development", speciality.getName());
    }

    @Test
    void testSpecialityEqualsAndHashCode() {
        Speciality speciality1 = new Speciality();
        speciality1.setId(1L);

        Speciality speciality2 = new Speciality();
        speciality2.setId(1L);

        assertEquals(speciality1, speciality2);
        assertEquals(speciality1.hashCode(), speciality2.hashCode());
    }
}
