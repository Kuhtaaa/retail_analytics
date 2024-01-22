package edu.school21.application.repositories;

import edu.school21.application.entities.CommodityMatrixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityMatrixRepository extends JpaRepository<CommodityMatrixEntity, Long> {}
