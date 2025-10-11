package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.domain.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
    // CRUD et autres via JpaRepository et Repository

    // Derived query: find by status with sorting
    //List<Learner> findAllByStatusOrderByCreatedAtDesc(String status);

    // Projection: DTO via interface projection
    //List<UserSummary> findAllProjectedByStatus(String status);

    // JPQL personnalisé
    @Query("select l from Learner l where l.email like %:email%")
    List<Learner> findByEmailContaining(@Param("email") String mail);
}
