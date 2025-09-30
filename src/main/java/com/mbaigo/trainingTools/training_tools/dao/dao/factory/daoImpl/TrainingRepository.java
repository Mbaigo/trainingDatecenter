package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl;

import com.mbaigo.trainingTools.training_tools.beans.Training;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.GenerikRepository;

import java.util.List;

public interface TrainingRepository extends GenerikRepository<Training, Long> {
    // Derived query: find by status with sorting
    List<Training> findAllByStatusOrderByCreatedAtDesc(String status);
    List<Training> findByTitle(String title);


    // JPQL personnalisé
//    @Query("select t from Training t where t.categoie like %:mail%")
//    List<Training> findByEmailContaining(@Param("mail") String mail);
}
