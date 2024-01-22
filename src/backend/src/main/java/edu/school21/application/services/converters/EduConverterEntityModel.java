package edu.school21.application.services.converters;

public interface EduConverterEntityModel<Entity, Model> {
    Model entityToDto (final Entity entity);
    Entity dtoToEntity (final Model model);
}
