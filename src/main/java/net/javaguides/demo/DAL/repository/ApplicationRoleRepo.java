package net.javaguides.demo.DAL.repository;

import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRoleRepo extends JpaRepository<ApplicationRole, Integer> {
    Optional <ApplicationRole> findByRoleName(String roleName);
}
