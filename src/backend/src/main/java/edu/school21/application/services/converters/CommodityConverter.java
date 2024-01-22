package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.CommodityMatrixModel;
import edu.school21.application.entities.CommodityMatrixEntity;
import org.springframework.stereotype.Component;

@Component
public class CommodityConverter implements EduConverterEntityModel<CommodityMatrixEntity, CommodityMatrixModel> {
    @Override
    public CommodityMatrixModel entityToDto(final CommodityMatrixEntity entity) {
        return new CommodityMatrixModel(
                String.valueOf(entity.getSkuId()),
                entity.getSkuName(),
                String.valueOf(entity.getGroupId())
        );
    }

    @Override
    public CommodityMatrixEntity dtoToEntity(final CommodityMatrixModel dto) {
        return new CommodityMatrixEntity(
                Long.parseLong(dto.getSkuId()),
                dto.getSkuName(),
                Long.parseLong(dto.getGroupId())
        );
    }
}