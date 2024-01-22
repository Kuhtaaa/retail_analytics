package edu.school21.application.graphql.mutations;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.PersonalInformationModel;
import edu.school21.application.services.PersonalInformationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class PersonalInformationMutations {

    private PersonalInformationService service;

    public boolean createPersonalInformation(
            final String customerName,
            final String customerSurname,
            final String customerPrimaryEmail,
            final String customerPrimaryPhone
    ) {
        service.created(
                new PersonalInformationModel(
                    "0",
                    customerName,
                    customerSurname,
                    customerPrimaryEmail,
                    customerPrimaryPhone
                )
        );
        return true;
    }

    public boolean updatePersonalInformation(
        final String customerId,
        final String customerName,
        final String customerSurname,
        final String customerPrimaryEmail,
        final String customerPrimaryPhone
    ) {
        service.update(
                new PersonalInformationModel(
                        customerId,
                        customerName,
                        customerSurname,
                        customerPrimaryEmail,
                        customerPrimaryPhone
                )
        );
        return true;
    }

    public boolean deletePersonalInformation(final String customerId) {
        try {
            service.delete(customerId);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }
}
