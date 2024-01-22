package edu.school21.application.repositories;

import edu.school21.application.entities.PersonalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformationEntity, Long> {}
