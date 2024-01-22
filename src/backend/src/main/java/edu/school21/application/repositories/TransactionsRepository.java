package edu.school21.application.repositories;

import edu.school21.application.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(
            value = "select * from transactions where transaction_datetime > :dateTimeBegin AND transaction_datetime < :dateTimeEnd",
            nativeQuery = true
    )
    List<TransactionEntity> findTransactionEntitiesBetweenDate(
            @Param("dateTimeBegin") final Timestamp dateTimeBegin,
            @Param("dateTimeEnd") final Timestamp dateTimeEnd);
}

