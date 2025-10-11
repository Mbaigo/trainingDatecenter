package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.mbaigo.trainingtools.training_tools.domain.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
}
