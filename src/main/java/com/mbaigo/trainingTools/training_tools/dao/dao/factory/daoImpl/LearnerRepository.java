package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl;

import com.mbaigo.trainingTools.training_tools.beans.Learner;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.GenerikRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearnerRepository extends JpaRepository<Learner, Long> {
    // CRUD et autres via JpaRepository et Repository

    // Derived query: find by status with sorting
    //List<Learner> findAllByStatusOrderByCreatedAtDesc(String status);

    // Projection: DTO via interface projection
    //List<UserSummary> findAllProjectedByStatus(String status);

    // JPQL personnalisé
    @Query("select l from Learner l where l.mailAdress like %:mailAdress%")
    List<Learner> findByEmailContaining(@Param("mailAdress") String mail);
}
