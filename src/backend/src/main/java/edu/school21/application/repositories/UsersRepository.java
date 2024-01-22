package edu.school21.application.repositories;

import edu.school21.application.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByUserNameOrEmail(final String userName, final String email);
}
