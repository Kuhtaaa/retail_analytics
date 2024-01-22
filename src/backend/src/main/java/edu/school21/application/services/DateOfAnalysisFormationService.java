package edu.school21.application.services;

import edu.school21.application.entities.DateOfAnalysisFormationEntity;
import edu.school21.application.graphql.models.DateOfAnalysisFormationModel;
import edu.school21.application.repositories.DateOfAnalysisFormationRepository;
import edu.school21.application.services.converters.DateOfAnalysisFormationConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service("date_of_analysis_formation")
public class DateOfAnalysisFormationService implements EduService<DateOfAnalysisFormationEntity, DateOfAnalysisFormationModel> {

    private DateOfAnalysisFormationRepository repository;
    private DateOfAnalysisFormationConverter converter;

    @Override
    @Transactional
    public DateOfAnalysisFormationEntity created(final DateOfAnalysisFormationModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    @Transactional
    public DateOfAnalysisFormationEntity update(final DateOfAnalysisFormationModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    public List<DateOfAnalysisFormationModel> findAll() {
        return repository
                .findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "id")))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }
    @Override
    public DateOfAnalysisFormationModel findById(final String id) {
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