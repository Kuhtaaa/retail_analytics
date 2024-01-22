package edu.school21.application.repositories;

import edu.school21.application.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<CardEntity, Long> {}
