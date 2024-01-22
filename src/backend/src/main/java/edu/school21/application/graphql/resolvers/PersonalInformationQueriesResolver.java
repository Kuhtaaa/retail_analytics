package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.PersonalInformationModel;
import edu.school21.application.graphql.queries.PersonalInformationQueries;
import edu.school21.application.services.PersonalInformationService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PersonalInformationQueriesResolver implements GraphQLResolver<PersonalInformationQueries> {

    private PersonalInformationService service;

    public List<PersonalInformationModel> getAllPersonalInformation(
            final PersonalInformationQueries queries
    ) {
        return service.findAll();
    }

    public PersonalInformationModel getPersonalInformationById(
            final PersonalInformationQueries queries,
            final String id
    ) {
        return service.findById(id);
    }
}
