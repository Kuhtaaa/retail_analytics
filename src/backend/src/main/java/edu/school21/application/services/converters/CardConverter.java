package edu.school21.application.services.converters;

import edu.school21.application.graphql.models.CardModel;
import edu.school21.application.entities.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardConverter implements EduConverterEntityModel<CardEntity, CardModel> {
    @Override
    public CardModel entityToDto(final CardEntity entity) {
        return new CardModel(
                String.valueOf(entity.getCustomerCardId()),
                String.valueOf(entity.getCustomerId())
        );
    }

    @Override
    public CardEntity dtoToEntity(final CardModel dto) {
        return new CardEntity(
                Long.parseLong(dto.getCustomerCardId()),
                Long.parseLong(dto.getCustomerId())
        );
    }
}