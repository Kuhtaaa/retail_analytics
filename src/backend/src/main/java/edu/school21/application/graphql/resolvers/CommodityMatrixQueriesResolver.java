package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.CommodityMatrixModel;
import edu.school21.application.graphql.queries.CommodityMatrixQueries;
import edu.school21.application.services.CommodityMatrixService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CommodityMatrixQueriesResolver implements GraphQLResolver<CommodityMatrixQueries> {

    private CommodityMatrixService service;

    public List<CommodityMatrixModel> getAllCommodityMatrices(
            final CommodityMatrixQueries commodityMatrixQueries
    ) {
        return service.findAll();
    }

    public CommodityMatrixModel getCommodityMatrixById(
            final CommodityMatrixQueries commodityMatrixQueries,
            final String id
    ) {
        return service.findById(id);
    }
}
