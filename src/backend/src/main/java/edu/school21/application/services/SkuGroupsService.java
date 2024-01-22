package edu.school21.application.services;

import edu.school21.application.entities.SkuGroupsEntity;
import edu.school21.application.graphql.models.SkuGroupModel;
import edu.school21.application.repositories.SkuGroupsRepository;
import edu.school21.application.services.converters.SkuGroupsConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("sku_groups")
@AllArgsConstructor
public class SkuGroupsService implements EduService<SkuGroupsEntity, SkuGroupModel> {
    private SkuGroupsRepository repository;
    private SkuGroupsConverter converter;

    @Override
    @Transactional
    public SkuGroupsEntity created(final SkuGroupModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    @Transactional
    public SkuGroupsEntity update(final SkuGroupModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    public List<SkuGroupModel> findAll() {
        return repository
                .findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "groupId")))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public SkuGroupModel findById(final String id) {
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