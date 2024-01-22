package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.StoreModel;
import edu.school21.application.entities.StoreEntity;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter implements EduConverterEntityModel<StoreEntity, StoreModel> {
    @Override
    public StoreModel entityToDto(final StoreEntity entity) {
        return new StoreModel(
                String.valueOf(entity.getTransactionStoreId()),
                String.valueOf(entity.getSkuId()),
                String.valueOf(entity.getSkuPurchasePrice()),
                String.valueOf(entity.getSkuRetailPrice())
        );
    }

    @Override
    public StoreEntity dtoToEntity(final StoreModel dto) {
        return new StoreEntity(
                Long.parseLong(dto.getTransactionStoreId()),
                Long.parseLong(dto.getSkuId()),
                Double.parseDouble(dto.getSkuPurchasePrice()),
                Double.parseDouble(dto.getSkuRetailPrice())
        );
    }
}