package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.PersonalInformationModel;
import edu.school21.application.entities.PersonalInformationEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonalInformationConverter implements
        EduConverterEntityModel<PersonalInformationEntity, PersonalInformationModel>
{
    @Override
    public PersonalInformationModel entityToDto(final PersonalInformationEntity entity) {
        return new PersonalInformationModel(
                String.valueOf(entity.getCustomerId()),
                entity.getCustomerName(),
                entity.getCustomerSurname(),
                entity.getCustomerPrimaryEmail(),
                entity.getCustomerPrimaryPhone()
        );
    }
    @Override
    public PersonalInformationEntity dtoToEntity(final PersonalInformationModel dto) {
        return new PersonalInformationEntity(
                Long.parseLong(dto.getCustomerId()),
                dto.getCustomerName(),
                dto.getCustomerSurname(),
                dto.getCustomerPrimaryEmail(),
                dto.getCustomerPrimaryPhone()
        );
    }
}