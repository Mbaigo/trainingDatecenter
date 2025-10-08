package com.mbaigo.trainingtools.training_tools.repository.user;

import com.mbaigo.trainingtools.training_tools.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
