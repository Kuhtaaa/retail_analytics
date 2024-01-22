package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.CheckModel;
import edu.school21.application.entities.CheckEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckConverter implements EduConverterEntityModel<CheckEntity, CheckModel> {
    @Override
    public CheckModel entityToDto(final CheckEntity entity) {
        return new CheckModel(
                String.valueOf(entity.getTransactionId()),
                String.valueOf(entity.getSkuId()),
                String.valueOf(entity.getSkuAmount()),
                String.valueOf(entity.getSkuSumm()),
                String.valueOf(entity.getSkuSummPaid()),
                String.valueOf(entity.getSkuDiscount())
        );
    }

    @Override
    public CheckEntity dtoToEntity(final CheckModel dto) {
        return new CheckEntity(
                Long.parseLong(dto.getTransactionId()),
                Long.parseLong(dto.getSkuId()),
                Long.parseLong(dto.getSkuAmount()),
                Long.parseLong(dto.getSkuSumm()),
                Long.parseLong(dto.getSkuSummPaid()),
                Long.parseLong(dto.getSkuDiscount())
        );
    }
}