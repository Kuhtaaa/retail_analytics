package edu.school21.application.repositories;

import edu.school21.application.entities.DateOfAnalysisFormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateOfAnalysisFormationRepository extends JpaRepository<DateOfAnalysisFormationEntity, Long> {}
