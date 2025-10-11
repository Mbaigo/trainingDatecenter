package com.mbaigo.trainingtools.training_tools.user.entities.admin;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String message;
    private String type; // INFO, AVERTISSEMENT, PROMO
    private boolean lu;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
