package com.mbaigo.trainingtools.training_tools.auth.token;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token; // uuid

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    private Instant expiryDate;

    private boolean revoked = false;

    // getters/setters
}

