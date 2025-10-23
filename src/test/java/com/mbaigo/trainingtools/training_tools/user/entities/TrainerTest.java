package com.mbaigo.trainingtools.training_tools.user.entities;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

    @Test
    void testTrainerConstructorAndGetters() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setEmail("john.doe@example.com");

        assertEquals("John", trainer.getFirstName());
        assertEquals("Doe", trainer.getLastName());
        assertEquals("john.doe@example.com", trainer.getEmail());
    }

    @Test
    void testTrainerEqualsAndHashCode() {
        Trainer trainer1 = new Trainer();
        trainer1.setId(1L);

        Trainer trainer2 = new Trainer();
        trainer2.setId(1L);

        assertEquals(trainer1, trainer2);
        assertEquals(trainer1.hashCode(), trainer2.hashCode());
    }
}
