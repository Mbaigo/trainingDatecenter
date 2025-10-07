package com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.domaine.userRole;

import com.mbaigo.trainingTools.training_tools.beans.userRole.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
