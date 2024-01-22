package edu.school21.application.graphql.mutations;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.CommodityMatrixModel;
import edu.school21.application.services.CommodityMatrixService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class CommodityMatrixMutations {

    private CommodityMatrixService service;

    public boolean createCommodityMatrix(final String skuName, final String groupId) {
        service.created(new CommodityMatrixModel("0", skuName, groupId));
        return true;
    }

    public boolean updateCommodityMatrix(
            final String skuId,
            final String skuName,
            final String groupId
    ) {
        service.update(
                new CommodityMatrixModel(
                        skuId,
                        skuName,
                        groupId
                )
        );
        return true;
    }

    public boolean deleteCommodityMatrix(final String id) {
        try {
            service.delete(id);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }
}
