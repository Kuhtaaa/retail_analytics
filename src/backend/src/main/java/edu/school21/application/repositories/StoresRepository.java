package edu.school21.application.repositories;

import edu.school21.application.entities.StoreEntity;
import edu.school21.application.entities.StoreEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<StoreEntity, StoreEntityPK> {}
