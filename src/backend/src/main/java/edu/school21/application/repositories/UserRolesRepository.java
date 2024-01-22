package edu.school21.application.repositories;

import edu.school21.application.entities.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {}
