package com.mbaigo.trainingtools.training_tools.auth.repositories;

import com.mbaigo.trainingtools.training_tools.auth.dto.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByEmailAndOtp(String email, String otp);
    Optional<PasswordResetToken> findByEmail(String email);
}

