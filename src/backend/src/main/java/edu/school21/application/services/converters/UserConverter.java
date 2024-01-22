package edu.school21.application.services.converters;

import edu.school21.application.entities.UsersEntity;
import edu.school21.application.graphql.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements EduConverterEntityModel<UsersEntity, UserModel> {
    @Override
    public UserModel entityToDto(final UsersEntity entity) {
        return new UserModel(
                String.valueOf(entity.getId()),
                entity.getEmail(),
                entity.getName(),
                entity.getUserName()
        );
    }

    @Override
    public UsersEntity dtoToEntity(final UserModel dto) {
        return null;
    }
}