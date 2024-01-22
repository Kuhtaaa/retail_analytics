package edu.school21.application.repositories;

import edu.school21.application.entities.CheckEntity;
import edu.school21.application.entities.CheckEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecksRepository extends JpaRepository<CheckEntity, CheckEntityPK> {}
