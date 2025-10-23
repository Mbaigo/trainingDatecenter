package com.mbaigo.trainingtools.training_tools.user.entities;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Learner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LearnerTest {

    @Test
    void testLearnerConstructorAndGetters() {
        Learner learner = new Learner();
        learner.setFirstName("Jane");
        learner.setLastName("Doe");
        learner.setEmail("jane.doe@example.com");

        assertEquals("Jane", learner.getFirstName());
        assertEquals("Doe", learner.getLastName());
        assertEquals("jane.doe@example.com", learner.getEmail());
    }

    @Test
    void testLearnerEqualsAndHashCode() {
        Learner learner1 = new Learner();
        learner1.setId(1L);

        Learner learner2 = new Learner();
        learner2.setId(1L);

        assertEquals(learner1, learner2);
        assertEquals(learner1.hashCode(), learner2.hashCode());
    }
}
