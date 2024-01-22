package edu.school21.application.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions", schema = "public", catalog = "retail21java")
public class TransactionEntity extends EduEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "transaction_id", nullable = false)
    private long transactionId;
    @Basic
    @Column(name = "customer_card_id", nullable = false)
    private long customerCardId;
    @Basic
    @Column(name = "transaction_summ", nullable = false)
    private double transactionSumm;
    @Basic
    @Column(name = "transaction_datetime")
    private Timestamp transactionDatetime;
    @Basic
    @Column(name = "transaction_store_id", nullable = false)
    private long transactionStoreId;
}
