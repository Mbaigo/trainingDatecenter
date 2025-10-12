package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String s);
}
