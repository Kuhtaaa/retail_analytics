package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.CheckModel;
import edu.school21.application.graphql.queries.CheckQueries;
import edu.school21.application.services.CheckService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CheckQueriesResolver implements GraphQLResolver<CheckQueries> {

    private CheckService service;

    public List<CheckModel> getAllChecks(final CheckQueries checkQueries) {
        return service.findAll();
    }

    public CheckModel getCheckById(
            final CheckQueries checkQueries,
            final String transactionId,
            final String skuId
    ) {
        return service.findById(transactionId, skuId);
    }
}
