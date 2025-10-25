package com.mbaigo.trainingtools.training_tools.auth.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenRefreshRequestTest {

    @Test
    void testTokenRefreshRequestConstructorAndGetters() {
        TokenRefreshRequest request = new TokenRefreshRequest();
        request.setRefreshToken("sample-refresh-token");

        assertEquals("sample-refresh-token", request.getRefreshToken());
    }

    @Test
    void testTokenRefreshRequestEqualsAndHashCode() {
        TokenRefreshRequest request1 = new TokenRefreshRequest();
        request1.setRefreshToken("sample-refresh-token");

        TokenRefreshRequest request2 = new TokenRefreshRequest();
        request2.setRefreshToken("sample-refresh-token");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
    }
}
