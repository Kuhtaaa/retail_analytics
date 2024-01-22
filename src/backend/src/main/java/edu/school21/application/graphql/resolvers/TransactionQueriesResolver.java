package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.TransactionModel;
import edu.school21.application.graphql.queries.TransactionQueries;
import edu.school21.application.services.TransactionsService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TransactionQueriesResolver implements GraphQLResolver<TransactionQueries> {

    private TransactionsService service;

    public List<TransactionModel> getAllTransactions(final TransactionQueries transactionQueries) {
        return service.findAll();
    }

    public TransactionModel getTransactionById(
            final TransactionQueries transactionQueries,
            final String id
    ) {
        return service.findById(id);
    }

    public List<TransactionModel> getTransactionEntitiesBetweenDate(
            final TransactionQueries transactionQueries,
            final String dateTimeBegin,
            final String dateTimeEng
    ) {
        return service.findTransactionEntitiesBetweenDate(dateTimeBegin, dateTimeEng);
    }
}
