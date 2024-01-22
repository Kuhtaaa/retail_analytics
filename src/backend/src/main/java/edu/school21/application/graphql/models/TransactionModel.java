package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionModel extends EduModel {
    private final String transactionId;
    private final String customerCardId;
    private final String transactionSumm;
    private final String transactionDatetime;
    private final String transactionStoreId;
}
