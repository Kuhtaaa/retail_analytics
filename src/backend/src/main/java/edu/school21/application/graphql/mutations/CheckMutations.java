package edu.school21.application.graphql.mutations;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.CheckModel;
import edu.school21.application.services.CheckService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class CheckMutations {

    private CheckService service;

    public boolean createCheck(
            final String transactionId,
            final String skuId,
            final String skuAmount,
            final String skuSumm,
            final String skuSummPaid,
            final String skuDiscount
    ) {
        if (service.existsById(transactionId, skuId)) {
            throw new ValidationGraphQLException(InfoMessages.CREATION_ERROR.getName());
        }

        service.created(
                new CheckModel(
                        transactionId,
                        skuId,
                        skuAmount,
                        skuSumm,
                        skuSummPaid,
                        skuDiscount
                )
        );
        return true;
    }

    public boolean updateCheck(
            final String transactionId,
            final String skuId,
            final String skuAmount,
            final String skuSumm,
            final String skuSummPaid,
            final String skuDiscount
    ) {
        service.update(
                new CheckModel(
                        transactionId,
                        skuId,
                        skuAmount,
                        skuSumm,
                        skuSummPaid,
                        skuDiscount
                )
        );
        return true;
    }

    public boolean deleteCheck(final String transactionId, final String skuId) {
        try {
            service.delete(transactionId, skuId);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }

}
