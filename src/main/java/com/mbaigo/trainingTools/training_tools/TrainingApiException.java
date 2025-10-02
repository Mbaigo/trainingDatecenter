package com.mbaigo.trainingTools.training_tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingApiException {
    private LocalDateTime timestamp;
    private int status;          // code HTTP
    private String message;      // message de l'exception
    private String details;      // détails supplémentaires (ex : URI)
}
