package edu.school21.application.graphql.mutations;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.DateOfAnalysisFormationModel;
import edu.school21.application.services.DateOfAnalysisFormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class DateOfAnalysisFormationMutations {

    private DateOfAnalysisFormationService service;

    public boolean createDateOfAnalysisFormation(final String analysisFormation) {
        service.created(new DateOfAnalysisFormationModel("0", analysisFormation));
        return true;
    }

    public boolean updateDateOfAnalysisFormation(
            final String id,
            final String analysisFormation
    ) {
        service.update(new DateOfAnalysisFormationModel(id, analysisFormation));
        return true;
    }

    public boolean deleteDateOfAnalysisFormation(final String id) {
        try {
            service.delete(id);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }

}
