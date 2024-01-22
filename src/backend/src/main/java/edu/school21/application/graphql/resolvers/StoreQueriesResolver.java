package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.StoreModel;
import edu.school21.application.graphql.queries.StoreQueries;
import edu.school21.application.services.StoresService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StoreQueriesResolver implements GraphQLResolver<StoreQueries> {

    private StoresService service;

    public List<StoreModel> getAllStores(final StoreQueries storeQueries) {
        return service.findAll();
    }

    public StoreModel getStoreById(
            final StoreQueries storeQueries,
            final String transactionStoreId,
            final String skuId
    ) {
        return service.findById(transactionStoreId, skuId);
    }
}
