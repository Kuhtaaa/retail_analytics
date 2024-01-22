package edu.school21.application.repositories;

import edu.school21.application.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {}
