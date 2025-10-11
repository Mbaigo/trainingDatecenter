package com.mbaigo.trainingtools.training_tools.exception;

public class TokenRefreshException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String refreshToken;

    public TokenRefreshException(String refreshToken, String message) {
        super(message);
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
