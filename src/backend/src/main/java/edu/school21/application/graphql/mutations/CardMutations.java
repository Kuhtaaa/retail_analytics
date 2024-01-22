package edu.school21.application.graphql.mutations;

import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.CardModel;
import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.services.CardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class CardMutations {

    private CardsService service;

    public boolean createCard(
            final String customerId
    ) {
        service.created(new CardModel("0", customerId));
        return true;
    }

    public boolean updateCard(
            final String customerCardId,
            final String customerId
    ) {
        service.update(new CardModel(customerCardId, customerId));
        return true;
    }

    public boolean deleteCard(final String id) {
        try {
            service.delete(id);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }
}
