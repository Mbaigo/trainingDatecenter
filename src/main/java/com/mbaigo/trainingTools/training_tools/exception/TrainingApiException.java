package com.mbaigo.trainingTools.training_tools.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
