package edu.school21.application.repositories;

import edu.school21.application.entities.SkuGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuGroupsRepository extends JpaRepository<SkuGroupsEntity, Long> {}
