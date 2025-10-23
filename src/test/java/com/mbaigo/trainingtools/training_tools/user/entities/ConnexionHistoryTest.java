package com.mbaigo.trainingtools.training_tools.user.entities;

import com.mbaigo.trainingtools.training_tools.user.entities.users.ConnexionHistory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConnexionHistoryTest {

    @Test
    void testConnexionHistoryConstructorAndGetters() {
        ConnexionHistory history = new ConnexionHistory();
        history.setAdresseIP("192.168.1.1");
        history.setDateConnexion(LocalDateTime.of(2025, 10, 24, 10, 0));

        assertEquals("192.168.1.1", history.getAdresseIP());
        assertEquals(LocalDateTime.of(2025, 10, 24, 10, 0), history.getDateConnexion());
    }

    @Test
    void testConnexionHistoryEqualsAndHashCode() {
        ConnexionHistory history1 = new ConnexionHistory();
        history1.setId(1L);

        ConnexionHistory history2 = new ConnexionHistory();
        history2.setId(1L);

        assertEquals(history1, history2);
        assertEquals(history1.hashCode(), history2.hashCode());
    }
}
