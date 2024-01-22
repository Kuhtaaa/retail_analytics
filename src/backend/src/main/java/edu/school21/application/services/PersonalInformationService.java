package edu.school21.application.services;

import edu.school21.application.entities.PersonalInformationEntity;
import edu.school21.application.graphql.models.PersonalInformationModel;
import edu.school21.application.repositories.PersonalInformationRepository;
import edu.school21.application.services.converters.PersonalInformationConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("personal_information")
@AllArgsConstructor
public class PersonalInformationService implements EduService<PersonalInformationEntity, PersonalInformationModel> {
    private PersonalInformationRepository repository;
    private PersonalInformationConverter converter;

    @Override
    @Transactional
    public PersonalInformationEntity created(final PersonalInformationModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    @Transactional
    public PersonalInformationEntity update(final PersonalInformationModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    public List<PersonalInformationModel> findAll() {
        return repository
                .findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "customerId")))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public PersonalInformationModel findById(final String id) {
        return converter.entityToDto(
                repository.findById(Long.parseLong(id))
                          .orElseThrow(RuntimeException::new)
        );
    }

    @Override
    @Transactional
    public void delete(final String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public boolean existsById(final String id) {
        return repository.existsById(Long.parseLong(id));
    }
}
