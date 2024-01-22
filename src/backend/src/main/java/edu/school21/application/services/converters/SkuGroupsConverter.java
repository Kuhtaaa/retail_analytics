package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.SkuGroupModel;
import edu.school21.application.entities.SkuGroupsEntity;
import org.springframework.stereotype.Component;

@Component
public class SkuGroupsConverter implements EduConverterEntityModel<SkuGroupsEntity, SkuGroupModel> {
    @Override
    public SkuGroupModel entityToDto(final SkuGroupsEntity entity) {
        return new SkuGroupModel(
                String.valueOf(entity.getGroupId()),
                entity.getGroupName()
        );
    }

    @Override
    public SkuGroupsEntity dtoToEntity(final SkuGroupModel dto) {
        return new SkuGroupsEntity(
                Long.parseLong(dto.getGroupId()),
                dto.getGroupName()
        );
    }
}