package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.DateOfAnalysisFormationModel;
import edu.school21.application.graphql.queries.DateOfAnalysisFormationQueries;
import edu.school21.application.services.DateOfAnalysisFormationService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DateOfAnalysisFormationQueriesResolver implements GraphQLResolver<DateOfAnalysisFormationQueries> {

    private DateOfAnalysisFormationService service;

    public List<DateOfAnalysisFormationModel> getAllDateOfAnalysisFormation(
            final DateOfAnalysisFormationQueries dateOfAnalysisFormationQueries
    ) {
        return service.findAll();
    }

    public DateOfAnalysisFormationModel getDateOfAnalysisFormationById(
            final DateOfAnalysisFormationQueries dateOfAnalysisFormationQueries,
            final String id
    ) {
        return service.findById(id);
    }
}
