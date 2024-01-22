package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.SkuGroupModel;
import edu.school21.application.graphql.queries.SkuGroupQueries;
import edu.school21.application.services.SkuGroupsService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SkuGroupQueriesResolver implements GraphQLResolver<SkuGroupQueries> {

    private SkuGroupsService service;

    public List<SkuGroupModel> getAllSkuGroups(final SkuGroupQueries skuGroupQueries) {
        return service.findAll();
    }

    public SkuGroupModel getSkuGroupById(final SkuGroupQueries skuGroupQueries, final String id) {
        return service.findById(id);
    }
}
