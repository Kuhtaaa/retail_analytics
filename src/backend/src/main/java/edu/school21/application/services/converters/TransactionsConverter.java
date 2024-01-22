package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.TransactionModel;
import edu.school21.application.entities.TransactionEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TransactionsConverter implements EduConverterEntityModel<TransactionEntity, TransactionModel> {
    @Override
    public TransactionModel entityToDto(final TransactionEntity entity) {
        return new TransactionModel(
                String.valueOf(entity.getTransactionId()),
                String.valueOf(entity.getCustomerCardId()),
                String.valueOf(entity.getTransactionSumm()),
                entity.getTransactionDatetime().toString(),
                String.valueOf(entity.getTransactionStoreId())
        );
    }

    @Override
    public TransactionEntity dtoToEntity(final TransactionModel dto) {
        return new TransactionEntity(
            Long.parseLong(dto.getTransactionId()),
            Long.parseLong(dto.getCustomerCardId()),
            Double.parseDouble(dto.getTransactionSumm()),
            Timestamp.valueOf(dto.getTransactionDatetime()),
            Long.parseLong(dto.getTransactionStoreId())
        );
    }
}