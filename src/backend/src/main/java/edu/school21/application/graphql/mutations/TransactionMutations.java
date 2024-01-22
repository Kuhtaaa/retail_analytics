package edu.school21.application.graphql.mutations;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.TransactionModel;
import edu.school21.application.services.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class TransactionMutations {

    private TransactionsService service;

    public boolean createTransaction(
            final String customerCardId,
            final String transactionSumm,
            final String transactionDatetime,
            final String transactionStoreId
    ) {
        service.created(
                new TransactionModel(
                        "0",
                        customerCardId,
                        transactionSumm,
                        transactionDatetime,
                        transactionStoreId
                )
        );
        return true;
    }

    public boolean updateTransaction(
            final String transactionId,
            final String customerCardId,
            final String transactionSumm,
            final String transactionDatetime,
            final String transactionStoreId
    ) {
        service.update(
                new TransactionModel(
                        transactionId,
                        customerCardId,
                        transactionSumm,
                        transactionDatetime,
                        transactionStoreId
                )
        );
        return true;
    }

    public boolean deleteTransaction(final String transactionId) {
        try {
            service.delete(transactionId);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }

}
