package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.DateOfAnalysisFormationModel;
import edu.school21.application.entities.DateOfAnalysisFormationEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DateOfAnalysisFormationConverter implements
        EduConverterEntityModel<DateOfAnalysisFormationEntity, DateOfAnalysisFormationModel>
{
    @Override
    public DateOfAnalysisFormationModel entityToDto(final DateOfAnalysisFormationEntity entity) {
        return new DateOfAnalysisFormationModel(
                String.valueOf(entity.getId()),
                entity.getAnalysisFormation().toString()
        );
    }

    @Override
    public DateOfAnalysisFormationEntity dtoToEntity(final DateOfAnalysisFormationModel dto) {
        return new DateOfAnalysisFormationEntity(
                Long.parseLong(dto.getId()),
                Timestamp.valueOf(dto.getAnalysisFormation())
        );
    }
}