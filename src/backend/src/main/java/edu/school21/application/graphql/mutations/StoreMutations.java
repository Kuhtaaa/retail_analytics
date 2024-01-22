package edu.school21.application.graphql.mutations;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.StoreModel;
import edu.school21.application.services.StoresService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class StoreMutations {

    private StoresService service;

    public boolean createStore(
            final String transactionStoreId,
            final String skuId,
            final String skuPurchasePrice,
            final String skuRetailPrice
    ) {
        if (service.existsById(transactionStoreId, skuId)) {
            throw new ValidationGraphQLException("Такая запись уже существует");
        }

        service.created(
                new StoreModel(
                        transactionStoreId,
                        skuId,
                        skuPurchasePrice,
                        skuRetailPrice
                )
        );
        return true;
    }

    public boolean updateStore(
            final String transactionStoreId,
            final String skuId,
            final String skuPurchasePrice,
            final String skuRetailPrice
    ) {
        service.update(
                new StoreModel(
                        transactionStoreId,
                        skuId,
                        skuPurchasePrice,
                        skuRetailPrice
                )
        );
        return true;
    }

    public boolean deleteStore(
            final String transactionStoreId,
            final String skuId
    ) {
        try {
            service.delete(transactionStoreId, skuId);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }

}
