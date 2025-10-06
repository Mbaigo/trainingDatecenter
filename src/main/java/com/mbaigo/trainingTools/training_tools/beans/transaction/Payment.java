package com.mbaigo.trainingTools.training_tools.beans.transaction;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Learner;
import com.mbaigo.trainingTools.training_tools.beans.domaine.Training;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private String statut; // SUCCES, ECHEC, EN_ATTENTE

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
