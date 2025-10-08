package com.mbaigo.trainingtools.training_tools.exception;

import lombok.Getter;

@Getter
public class TrainingApiException extends RuntimeException{
    private final int status;    // code HTTP
    private final String details;

    public TrainingApiException(String message, int status, String details) {
        super(message);
        this.status = status;
        this.details = details;
    }

    public TrainingApiException(String message, int status) {
        super(message);
        this.status = status;
        this.details = null;
    }     // détails supplémentaires (ex : URI)
}
