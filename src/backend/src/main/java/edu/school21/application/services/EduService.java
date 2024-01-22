package edu.school21.application.services;

import java.util.List;

public interface EduService<Entity, Dto> {

    Entity created(final Dto dto);

    Entity update(final Dto dto);

    List<Dto> findAll();

    Dto findById(final String id);

    void delete(final String id);

    boolean existsById(final String id);
}
